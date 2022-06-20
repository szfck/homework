#include <iostream>
#include "helper.h"
#include <vector>
#include <queue>
#include <cmath>
#include <sstream>
#include <set>
#include <unistd.h>
using namespace std;

// set CMD to false for web user
// set CMD to true to command line user
const bool CMD = false;

const unsigned int microseconds = 1000; // sleep 1 secs
const string OUTPUT = "../output/";
const string term_table_path = OUTPUT + "term_table.txt";
const string url_table_path = OUTPUT + "url_table.txt";
const string index_txt_path = OUTPUT + "intermediate-output-3/" + "index-00000.merge1.txt";
const string content_bin_path = OUTPUT + "url_content.bin";
const string index_bin_path = OUTPUT + "intermediate-output-3/" + "index-00000.merge1.bin";
const int MAXDID = 1e9 + 7; // MAX ID of doc, real doc id alway smaller than MAXDID
int N; // total number of docs
double doc_length_avg; // average length of total docs
map<vector<string>, vector<pair<int, double>>> cache; // query cache {"query term1", "term term2"} -> {{doc Id1, score1}, {doc Id2, score2} ... }

vector<Url> url_table; // uid -> url, length, content start, end

Reader content_bin; // content binary reader

unordered_map<string, int> terms; // term -> tid

vector<Term> term_table; // tid -> term

vector<Index> index_table; // tid -> tid, start, end, number

int getTermId(string s) {
    if (terms.find(s) == terms.end()) return -1;
    else return terms[s];
}
string getTerm(int tid) {return term_table[tid].term;}
int getUrlLen(int uid) {return url_table[uid].length;}

vector<string> getPayload(int uid);

string getSnippet(const vector<string>& doc,
        const vector<string>& query, int shift = 10);

void start();

vector<pair<int, double>> Query(vector<string> query); // [{doc Id, score} ...]
void outputResult(const vector<pair<int, double>>& result, const vector<string>& query);
vector<string> parseQuery(string query);

int main(int argc, char* argv[]) {
    start();

    string prequery = "";
    while (true) {
        usleep(microseconds); // sleep for
        if (CMD) {
            cout << "please input your query in a line, or quit to exit" << endl;
        }
        string query;
        if (CMD) { // for command line user
            getline(cin, query);
            if (query == "quit") break;

            vector<string> query_terms = parseQuery(query);

            auto result = Query(query_terms);

            outputResult(result, query_terms);
        } else { // for web user
            ifstream in;
            in.open("../in.txt");
            string query;
            // reader query from in.txt (node server already writen to it)
            getline(in, query);
            in.close();

            if (query == "") {
                continue;
            }

            if (query != prequery) { // if it's a new query
                cout << "get a query " << query << endl;
                vector<string> query_terms = parseQuery(query);

                // get query result
                auto result = Query(query_terms);

                cout << "already get result" << endl;

                ofstream out;
                out.open("../out.txt");
                // for each found url, output url, bm25 value and snippet to out.txt
                // node server will read the result from out.txt
                for (auto pair : result) {
                    int uid = pair.first;
                    double value = pair.second;
                    string url = url_table[uid].url;
                    auto payload = getPayload(uid);
                    out << url << endl;
                    out << value << endl;
                    string snnipet = getSnippet(payload, query_terms);
                    out << snnipet << endl;
                }
                out.close();
            }
            prequery = query;
        }

    }
    return 0;
}

// parse query terms
vector<string> parseQuery(string query) {
    stringstream ss(query);
    vector<string> query_terms;
    string word;
    cout << "parsing..." << endl;
    while (ss >> word) {
        query_terms.push_back(word);
        cout << word << endl;
    }
    cout << "parsed" << endl;
    return query_terms;
}

struct StreamReader {
public:
    map<int, Doc> docs;
    void open(int tid) {
        index_bin.open(index_bin_path);
        auto docArr = index_bin.vreadList(tid,
                                   index_table[tid].start,
                                   index_table[tid].end,
                                   index_table[tid].number);
        for (Doc doc : docArr) {
            docs[doc.uid] = doc;
        }
    }
    void close() {
        index_bin.close();
        docs.clear();
    }

private:
    Reader index_bin;
};

// open a new streamReader for given term id
StreamReader openList(int tid) {
    StreamReader streamReader;
    streamReader.open(tid);
    return streamReader;
}

// close streamReader
void closeList(StreamReader& streamReader) {
    streamReader.close();
}

// get freq of uid in streamReader
int getFreq(StreamReader& streamReader, int uid) {
    return streamReader.docs[uid].freq;
}

// get next url id which is larger or equal to uid in streamReader
int nextGEQ(StreamReader& streamReader, int uid) {
    auto next_itr = streamReader.docs.lower_bound(uid);
    if (next_itr == streamReader.docs.end()) {
        return MAXDID;
    } else {
        return next_itr->second.uid;
    }
}

// f_t : number of doc that contain term t
// f_d_t: freq of term t in doc d
double BM25(int doc_length, const vector<int>& f_d_t, const vector<int>& f_t) {
    double k1 = 1.2, b = 0.75;
    double k = k1 * ((1 - b) + b * doc_length / doc_length_avg);
    double sum = 0.0;
    assert (f_d_t.size() == f_t.size());
    int num = f_d_t.size();
    for (int i = 0; i < num; i++) {
        sum += log((N - f_t[i] + 0.5) / (f_t[i] + 0.5)) *
                ((k1 + 1) * f_d_t[i] / (k + f_d_t[i]));
    }
    return sum;
}

// DAAT query
vector<pair<int, double>> Query(vector<string> query) {
    if (cache.find(query) != cache.end()) {
        return cache[query];
    }
    int top = 15; // choose top 15
    priority_queue<
            pair<double, int>,
            vector<pair<double, int>>,
            greater<pair<double, int>> > pq;

    vector<int> termIds;

    for (int i = 0; i < (int) query.size(); i++) {
        int tid = getTermId(query[i]);
        if (tid == -1) {
            cout << "not find " << query[i] << endl;
            continue;
        }
        termIds.push_back(tid);
    }
    int n = termIds.size();
    if (n <= 0) {
        return {};
    }
    vector<StreamReader> readers(n);
    for (int i = 0; i < n; i++) {
        readers[i] = openList(termIds[i]);
    }
    int did = 0;
    while (did < MAXDID) {
        // get next post from shortest list
        did = nextGEQ(readers[0], did);

        int d = did;
        for (int i = 1; (i < n) && ((d = nextGEQ(readers[i], did)) == did); i++);

        if (did == MAXDID) break;
        if (d > did) did = d;
        else {
            vector<int> f_d_t(n, 0);
            vector<int> f_t(n, 0);
            for (int i = 0; i < n; i++) {
                f_d_t[i] = getFreq(readers[i], did);
                f_t[i] = index_table[termIds[i]].number;
            }
            int doc_length = getUrlLen(did);

            // compute BM25
            double score = BM25(doc_length, f_d_t, f_t);

            pq.emplace(score, did);
            if (pq.size() > top) {
                pq.pop();
            }

            did++;
        }

    }

    for (int i = 0; i < n; i++) {
        closeList(readers[i]);
    }
    vector<pair<int, double>> result;
    while (pq.size()) {
        auto cur = pq.top();
        pq.pop();
        result.emplace_back(cur.second, cur.first);
    }
    reverse(result.begin(), result.end());

    cache[query] = result;
    return result;
}

// output result for command line user
void outputResult(const vector<pair<int, double>>& result, const vector<string>& query) {
    for (auto pair : result) {
        int uid = pair.first;
        double value = pair.second;
        string url = url_table[uid].url;
        if (url.size() < 4 || url.substr(0, 4) != "http") continue;
        auto payload = getPayload(uid);
        cout << url << " " << value << endl;

        string snnipet = getSnippet(payload, query);
        cout << snnipet << endl;
    }
}

// start search engine
// read term table, url table, index table
// open reader for content binary file
void start() {
    cout << "search engine starting ..." << endl;
    Reader term_table_reader(term_table_path);
    term_table = term_table_reader.termread();
    term_table_reader.close();

    for (auto term : term_table) {
        terms[term.term] = term.tid;
    }

    Reader index_reader(index_txt_path);
    index_table = index_reader.indexread();
    index_reader.close();

    Reader url_table_reader(url_table_path);
    url_table = url_table_reader.urlread();
    N = (int) url_table.size();
    cout << "number of doc: " << N << endl;
    long long total_doc_length = 0;
    for (Url url : url_table) {
        total_doc_length += url.length;
    }
    doc_length_avg = 1.0 * total_doc_length / N;
    cout << "doc length avg: " << doc_length_avg << endl;
    url_table_reader.close();

    content_bin.open(content_bin_path);

    cout << "search engine started" << endl;

}

vector<string> getPayload(int uid) {
    vector<int> list = content_bin.vread(
            url_table[uid].content_start_byte,
            url_table[uid].content_end_byte);
    vector<string> words;
    for (int tid : list) {
        words.push_back(term_table[tid].term);
    }
    return words;
}

// generate snippet
string getSnippet(const vector<string>& doc, const vector<string>& query, int shift) {
    vector<pair<int, int>> segments;
    set<string> vis;
    for (auto str : query) {
        vis.insert(str);
    }
    int len = doc.size();
    for (int i = 0; i < len; i++) {
        string str = doc[i];
        if (vis.find(str) != vis.end()) {
            vis.erase(str);
            segments.emplace_back(max(0, i - shift), min(len - 1, i + shift));
        }
    }
    sort(segments.begin(), segments.end());
    int ptr = 1;
    for (int i = 1; i < (int) segments.size(); i++) {
        if (segments[i].first <= segments[ptr - 1].second + 1) {
            segments[ptr - 1].second = max(segments[ptr - 1].second, segments[i].second);
        } else {
            segments[ptr++] = segments[i];
        }
    }
    string snnipet = "";
    const string ellipsis = "...... ";
    for (int i = 0; i < ptr; i++) {
        int l = segments[i].first, r = segments[i].second;
        if (i > 0) snnipet += ellipsis;
        for (int j = l; j <= r; j++) {
            snnipet += doc[j] + " ";
        }
    }
    return snnipet;
}

#include<iostream>
#include<stack>
#include<iomanip>
#include<algorithm>
#include<string>
#include<string.h>
#include<cmath>
#include<set>
#include<vector>
#include<queue>
#include<map>
#include<time.h>
using namespace std;
const int N = 2005;
using ll = long long;
const int INF = 0x3f3f3f3f;
using pii = pair<int, int>;
using vi = vector<int>;
#define P(x) { if (debug) cout << x << endl;}
#define H(x) P(#x << ": " << (x))
#define rep(i, a, b) for (int i = (a); i < (b); i++)
#define mp make_pair 
#define pb push_back
#define clr(x, v) memset(x, v, sizeof (x))
#define all(x) x.begin(), x.end()
#define sz(x) (int) x.size()
#define fi first
#define se second
const int MOD = 1e9 + 7;
const bool debug = 0;
int n, m;
using B = vector<int>;
int T;
B read() {
    B board(9);
    for (int i = 0; i < 9; i++) {
        cin >> board[i];
    }
    return board;
}
int dx[4] = { 0, 1, -1, 0 };
int dy[4] = { 1, 0, 0, -1 };
string getDir(int k) {
    if (k == 0) return "R";
    if (k == 1) return "D";
    if (k == 2) return "U";
    if (k == 3) return "L";
    assert (false);
}
map<B, int> bid;
map<int, B> boards;
int get(B b) {
    if (bid.find(b) == bid.end()) {
        int num = (int) bid.size();
        bid[b] = num;
        boards[num] = b;
    }
    return bid[b];
}
B get(int id) {
    return boards[id];
}
struct Node {
    int g, h;
    int b;
    Node(int g, int h, int b) : g(g), h(h), b(b) { }
    bool operator < (const Node& o) const {
        return h > o.h;
    }
};
int move(int b, int k) {
    B cur = get(b);
    int zid, zx, zy;
    for (int i = 0; i < 9; i++) {
        if (cur[i] == 0) {
            zid = i;
            zx = i / 3;
            zy = i % 3;
            break;
        }
    }
    int nx = zx + dx[k];
    int ny = zy + dy[k];
    if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) return -1;
    B tmp = cur;
    swap(tmp[zid], tmp[nx * 3 + ny]);
    int tid = get(tmp);
    return tid;
}
int man(int cur, int dest) {
    B from = get(cur), to = get(dest);
    vector<pair<int, int>> v1(9);
    vector<pair<int, int>> v2(9);
    for (int i = 0; i < 9; i++) {
        int x = i / 3, y = i % 3;
        v1[from[i]] = mp(x, y);
        v2[to[i]] = mp(x, y);
    }
    int sum = 0;
    for (int i = 1; i < 9; i++) {
        sum += abs(v1[i].first - v2[i].first) + abs(v1[i].second - v2[i].second);
    }
    return sum;
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);
    int start = get(read());
    int dest = get(read());
    priority_queue<Node> pq;
    map<int, int> pre;
    pq.emplace(0, man(start, dest), start);
    map<int, int> best;
    best[start] = man(start, dest);
    set<int> vis;
    while (pq.size()) {
        Node node = pq.top();
        pq.pop();
        int curid = node.b;
        if (curid == dest) {
            cout << "depth: " << node.g << endl;
            cout << "node gen: " << best.size() << endl;
            int p = dest;
            vector<int> arr;
            while (p != start) {
                int k = pre[p];
                arr.push_back(k);
                p = move(p, 3 - k);
            }
            reverse(all(arr));
            for (int k : arr) {
                cout << getDir(k) << " ";
            }
            cout << endl;
            break;
        }
        if (vis.find(curid) != vis.end()) {
            continue;
        }
        vis.insert(curid);
        for (int k = 0; k < 4; k++) {
            int tid = move(curid, k);
            if (tid < 0) continue;
            if (vis.find(tid) != vis.end()) {
                continue;
            }
            int ng = node.g + 1;
            int nh = ng + man(tid, dest);
            if (best.find(tid) == best.end() || best[tid] > nh) {
                best[tid] = nh;
                pre[tid] = k;
                pq.emplace(ng, nh, tid);
            }
        }
    }
    
    return 0;
}



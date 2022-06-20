#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
using namespace std;
int main() {
    string s;
    while (cin >> s) {
        stringstream ss(s);
        vector<int> vec;
        int x;
        while (ss >> x) {
            vec.push_back(x);
        }
        sort(vec.begin(), vec.end());
        for (int i = 0; i < (int) vec.size(); i++) {
            if (i > 0) cout << "+";
            cout << vec[i];
        }
        cout << endl;
    }
    return 0;
}

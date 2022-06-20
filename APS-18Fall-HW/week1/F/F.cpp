#include <iostream>
using namespace std;
int main() {
    char ch;
    int cnt = 0;
    string res = "";
    while ((ch = getchar()) != EOF) {
        if (ch == '"') {
            if (cnt == 0) {
                res += "``";
            } else {
                res += "''";
            }
            cnt = 1 - cnt;
        } else {
            res += ch;
        }
    }
    cout << res;
    return 0;
}

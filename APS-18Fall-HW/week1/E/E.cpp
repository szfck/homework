#include <iostream>
#include <string>
using namespace std;
int main() {
    int n;
    cin >> n;
    while (n--) {
        string s;
        cin >> s;
        string res = "";
        for (int i = 0; i < (int) s.size(); i++) {
            int j = i;
            while (j + 1 < (int) s.size() && s[j + 1] == s[j]) {
                j++;
            }
            int len = j - i + 1;
            if (len > 1) {
                cout << len;
            }
            cout << s[i];
            i = j;
        }
        cout << endl;
    }
    return 0;
}

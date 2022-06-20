#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
#define I "illegal"
#define F "first"
#define S "second"
#define FW "the first player won"
#define SW "the second player won"
#define D "draw"
bool check(char a, char b, char c) {
    return a != '.' && a == b && a == c;
}
bool win(string s[3]) {
    bool flag = false;
    for (int i = 0; i < 3; i++) {
        flag |= check(s[i][0], s[i][1], s[i][2]);
        flag |= check(s[0][i], s[1][i], s[2][i]);
    }
    flag |= check(s[0][0], s[1][1], s[2][2]);
    flag |= check(s[0][2], s[1][1], s[2][0]);
    return flag;
}
string solve() {
    string s[3];
    for (int i = 0; i < 3; i++) {
        cin >> s[i];
    }
    int X = 0, O = 0;
    int count = 0;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (s[i][j] == 'X') {
                count++;
                X++;
            } else if (s[i][j] == '0') {
                count++;
                O++;
            }
        }
    }
    char last;
    if (X == O) {
        last = '0';
    } else if (X == O + 1) {
        last = 'X';
    } else {
        return I;
    }
    if (!win(s)) {
        if (count == 9) return D;
        return (last == 'X' ? S : F);
    }
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            if (s[i][j] == last) {
                s[i][j] = '.';
                if (!win(s)) return (last == 'X' ? FW : SW);
                s[i][j] = last;
            }
        }
    }
    return I;
}
int main() {
    cout << solve() << endl;
    return 0;
}

#include<iostream>
#include<iomanip>
#include<algorithm>
#include<vector>
#include<string>
#include<cmath>
#include<map>
#include<string.h>
using namespace std;
int dp[101][101]; // dp[i][j] : #ways when sum = j and choose i numbers
const int MOD = 1e6;
int n, k;
void add(int& a, int b) {
    a += b;
    if (a >= MOD) a -= MOD;
}
int main() {
    while (cin >> n >> k) {
        if (n == 0 && k == 0) break;
        memset(dp, 0, sizeof dp);
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
                for (int t = 0; t <= n; t++) {
                    if (j + t <= n) {
                        add(dp[i][j + t], dp[i - 1][j]);
                    }
                }
            }
        }
        cout << dp[k][n] << endl;
    }
    return 0;
}

#include<iostream>
#include<iomanip>
#include<algorithm>
#include<vector>
#include<string>
#include<cmath>
#include<map>
#include<string.h>
using namespace std;
int dp[20002];
int n, m, x;
int main() {
    int T; cin >> T;
    while (T--) {
        cin >> m >> n;
        memset(dp, -1, sizeof dp);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            cin >> x;
            // since no coin value is larger than 10000, the result should be no larger than 20000
            for (int j = 20001 - x; j >= 0; j--) {
                if (dp[j] > -1) {
                    if (dp[j + x] == -1 || dp[j + x] > dp[j] + 1) {
                        dp[j + x] = dp[j] + 1;
                    }
                }
            }
        }
        for (int i = m; i <= 20001; i++) {
            if (dp[i] > -1) {
                cout << i << " " << dp[i] << endl;
                break;
            }
        }
    }
    return 0;
}

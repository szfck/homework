#include<iostream>
#include<queue>
#include<set>
#include<iomanip>
#include<algorithm>
#include<vector>
#include<string>
#include<cmath>
using namespace std;
const int INF = 0x3f3f3f3f;
int n, m;
int a[1000005];
int dp[2][1005];
int main() {
    while (cin >> n >> m) {
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }
        // if n > m, the answer is YES.
        // consider the prefix sum, according to the pigeonhole principle, there are sum equals sum modulo m
        if (n > m) {
            cout << "YES" << endl;
            continue;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = 0;
            }
        }
        int cur = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[cur ^ 1][j] = dp[cur][j];
            }
            int add = a[i] % m;
            for (int j = 0; j < m; j++) {
                if (dp[cur][j]) {
                    dp[cur ^ 1][(j + add) % m] = 1;
                }
            }
            dp[cur ^ 1][add] = 1;
            cur ^= 1;
        }
        cout << (dp[cur][0] ? "YES" : "NO") << endl;
    }
    return 0;
}

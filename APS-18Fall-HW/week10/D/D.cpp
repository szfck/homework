#include<iostream>
#include<algorithm>
#include<queue>
#include<vector>
#include<string>
#include<string.h>
using namespace std;
const int N = 2e5 + 10;
const int INF = 0x3f3f3f3f;
#define pb push_back
#define mp make_pair
#define fi first
#define se second
int n, m;
int dp[55][55]; // dp[i][j]: min cost for cutting segment [i,j]
int a[55];
int main() {
    while (cin >> m) {
        if (m == 0) break;
        cin >> n;
        for (int i = 1; i <= n; i++) {
            cin >> a[i];
        }
        a[0] = 0, a[n + 1] = m;
        for (int i = 0; i <= n; i++) {
            dp[i][i + 1] = 0;
        }
        for (int len = 2; len <= n + 1; len++) {
            for (int i = 0; i + len <= n + 1; i++) {
                int j = i + len;
                dp[i][j] = INF;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j]);
                }
                dp[i][j] += a[j] - a[i];
            }
        }
        cout << "The minimum cutting is " << dp[0][n + 1] << "." << endl;
    }
    return 0;
}

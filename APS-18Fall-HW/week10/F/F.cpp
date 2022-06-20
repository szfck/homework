#include<iostream>
#include<stack>
#include<algorithm>
#include<vector>
#include<string>
#include<string.h>
using namespace std;
const int INF = 0x3f3f3f3f;
int n, m;
int a[55];
// using rolling array
// dp[i][j] means in layer i, the maximum sum get when the difference of two towers is j
int dp[2][500005];
int cas = 1;
int main() {
    int T; cin >> T;
    while (T--) {
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> a[i];
        }
        memset(dp, -1, sizeof dp);
        int cur = 0;
        dp[cur][0] = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 500005; j++) {
                dp[cur ^ 1][j] = dp[cur][j];
            }
            for (int j = 0; j <= sum; j++) {
                if (dp[cur][j] < 0) continue;

                // add a[i] to the higher tower
                dp[cur ^ 1][j + a[i]] = max(dp[cur ^ 1][j + a[i]], dp[cur][j] + a[i]);

                // add a[i] to the lower tower
                int k = abs(j - a[i]);
                dp[cur ^ 1][k] = max(dp[cur ^ 1][k], dp[cur][j] + a[i]);
            }
            sum += a[i];
            cur ^= 1;
        }
        cout << "Case " << cas++ << ": ";
        if (dp[cur][0] <= 0) {
            cout << "impossible" << endl;
        } else {
            cout << dp[cur][0] / 2 << endl;
        }
    }
    return 0;
}

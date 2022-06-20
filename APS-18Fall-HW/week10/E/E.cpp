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
int weight[1005], load[1005];
// using rolling array
// dp[i][j] : in layer i, largest number of boxes could put with weight j
int dp[2][3005];
int n;
int main() {
    while (cin >> n) {
        if (n == 0) break;
        for (int i = 0; i < n; i++) {
            cin >> weight[i] >> load[i];
        }
        int cur = 0;
        for (int i = 0; i < 3005; i++) {
            dp[cur][i] = -INF;
        }
        dp[cur][0] = 0;

        int res = 0;

        // try to put box from top to bottom
        // cur means current layer, cur ^ 1 means next layer
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 3005; j++) {
                dp[cur ^ 1][j] = -INF;
            }
            for (int j = 0; j < 3005; j++) {
                if (dp[cur][j] < 0) continue;

                // not put box i
                dp[cur ^ 1][j] = max(dp[cur ^ 1][j], dp[cur][j]);

                // if load of box could support weight j, then try to add box i to the bottom
                if (load[i] >= j) {
                    res = max(res, 1 + dp[cur][j]);
                    if (j + weight[i] < 3005) {
                        dp[cur ^ 1][j + weight[i]] = max(dp[cur ^ 1][j + weight[i]], dp[cur][j] + 1);
                    }
                }
            }
            cur ^= 1;
        }
        cout << res << endl;
    }
    return 0;
}

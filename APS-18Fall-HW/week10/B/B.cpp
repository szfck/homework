#include<iostream>
#include<iomanip>
#include<algorithm>
#include<vector>
#include<string>
#include<cmath>
#include<map>
#include<string.h>
using namespace std;
const int N = 10005;
int a[N];
// dp[0][i] : LIS forward from 0 to i
// dp[1][i] : LIS backward from n - 1 to i
int dp[2][N];
int n;
// monotone stack + binary search  
// O(nlog(n))
void LIS(int* a, int n, int* dp) {
    vector<int> stk;
    for (int i = 0; i < n; i++) {
        // find first one >= a[i]
        int ptr = lower_bound(stk.begin(), stk.end(), a[i]) - stk.begin();
        if (ptr >= (int) stk.size()) {
            stk.push_back(a[i]);
        } else {
            stk[ptr] = a[i];
        }
        dp[i] = ptr + 1;
    }
}
int main() {
    while (cin >> n) {
        for (int i = 0; i < n; i++) cin >> a[i];
        LIS(a, n, dp[0]);
        reverse(a, a + n);
        LIS(a, n, dp[1]);
        reverse(dp[1], dp[1] + n);
        int res = 0;
        for (int i = 0; i < n; i++) {
            int len = min(dp[0][i], dp[1][i]);
            res = max(res, len * 2 - 1);
        }
        cout << res << endl;
    }
    return 0;
}

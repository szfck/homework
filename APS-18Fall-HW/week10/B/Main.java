import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static void reverse(int[] a) {
        int l = 0, r = a.length - 1;
        while (l < r) {
            int t = a[l];
            a[l] = a[r];
            a[r] = t;
            l++;
            r--;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int[][] dp = new int[2][n];
            LIS(a, n, dp[0]);
            reverse(a);
            LIS(a, n, dp[1]);
            reverse(dp[1]);
            int res = 0;
            for (int i = 0; i < n; i++) {
                int len = Math.min(dp[0][i], dp[1][i]);
                res = Math.max(res, len * 2 - 1);
            }
            System.out.println(res);
        }
    }

    private static void LIS(int[] a, int n, int[] dp) {
        List<Integer> stk = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int ptr = lower_bound(stk, a[i]);
            if (ptr >= stk.size()) {
                stk.add(a[i]);
            } else {
                stk.set(ptr, a[i]);
            }
            dp[i] = ptr + 1;
        }
    }

    private static int lower_bound(List<Integer> stk, int val) {
        int l = 0, r = stk.size() - 1;
        int res = r + 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (stk.get(m) >= val) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return res;
    }
}

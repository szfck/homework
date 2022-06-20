import java.util.*;
import java.io.*;

public class Main {
    private static final int MOD = 1000_000_000 + 7;
    private static final int MAX = 1000;
    private static int[] operation1(int n, int[] coins) {
        int[][] dp = new int[n + 1][MAX + 1]; // dp[i][s]
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = coins[i - 1];
            for (int s = 0; s <= MAX; s++) {
                for (int num = 0; num <= s / val; num++) {
                    dp[i][s] = (dp[i][s] + dp[i - 1][s - num * val]) % MOD;
                }
            }
        }
        return dp[n];
    }
    private static int[][] operation2(int n, int[] coins) {
        int[][][] dp = new int[n + 1][MAX + 1][MAX + 1]; // dp[i][s][k]
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int val = coins[i - 1];
            for (int s = 0; s <= MAX; s++) {
                for (int k = 0; k <= s; k++) {
                    dp[i][s][k] = dp[i - 1][s][k];
                    if (s >= val && k >= 1) {
                        dp[i][s][k] = (dp[i][s][k] + dp[i][s - val][k - 1]) % MOD;
                    }
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp1 = operation1(n, coins);
        int[][] dp2 = operation2(n, coins);
        int q = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int s = Integer.parseInt(st.nextToken());
                bw.write(String.valueOf(dp1[s]) + '\n');
            } else if (op == 2) {
                int s = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                bw.write(String.valueOf(dp2[s][k]) + '\n');
            } else {
                throw new RuntimeException();
            }
        }
        bw.flush();
    }
}
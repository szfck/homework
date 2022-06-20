import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MOD = 1000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (n == 0 && k == 0) break;
            int[][] dp = new int[k + 1][n + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= k; i++) {
                for (int j = 0; j <= n; j++) {
                    for (int t = 0; t <= n; t++) {
                        if (j + t <= n) {
                            dp[i][j + t] = (dp[i][j + t] + dp[i - 1][j]) % MOD;
                        }
                    }
                }
            }
            System.out.println(dp[k][n]);
        }
    }
}

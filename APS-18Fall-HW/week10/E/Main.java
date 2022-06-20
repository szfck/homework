import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            int[] weight = new int[n];
            int[] load = new int[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                weight[i] = Integer.parseInt(st.nextToken());
                load[i] = Integer.parseInt(st.nextToken());
            }
            int[][] dp = new int[2][3005];
            int cur = 0;
            for (int i = 0; i < 3005; i++) {
                dp[cur][i] = -1;
            }
            dp[cur][0] = 0;

            int res = 0;

            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < 3005; j++) {
                    dp[cur ^ 1][j] = -1;
                }
                for (int j = 0; j < 3005; j++) {
                    if (dp[cur][j] < 0) continue;
                    dp[cur ^ 1][j] = Math.max(dp[cur ^ 1][j], dp[cur][j]);

                    if (load[i] >= j) {
                        res = Math.max(res, 1 + dp[cur][j]);
                        if (j + weight[i] < 3005) {
                            dp[cur ^ 1][j + weight[i]] = Math.max(dp[cur ^ 1][j + weight[i]], dp[cur][j] + 1);
                        }
                    }
                }
                cur ^= 1;
            }
            System.out.println(res);
        }
    }

}
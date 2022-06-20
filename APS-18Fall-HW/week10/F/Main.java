import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] dp = new int[2][500005];
        int T = Integer.parseInt(br.readLine());
        int cas = 1;

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] a = new int[n];
            int tot = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                tot += a[i];
            }
            Arrays.fill(dp[0], -1);
            Arrays.fill(dp[1], -1);
            dp[0][0] = 0;
            int cur = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= tot; j++) {
                    dp[cur ^ 1][j] = dp[cur][j];
                }
                for (int j = 0; j <= sum; j++) {
                    if (dp[cur][j] < 0) continue;

                    dp[cur ^ 1][j + a[i]] = Math.max(dp[cur ^ 1][j + a[i]], dp[cur][j] + a[i]);

                    int k = Math.abs(j - a[i]);
                    dp[cur ^ 1][k] = Math.max(dp[cur ^ 1][k], dp[cur][j] + a[i]);
                }
                cur ^= 1;
                sum += a[i];
            }
            if (dp[cur][0] <= 0) {
                System.out.println("Case " + cas++ + ": impossible");
            } else {
                System.out.println("Case " + cas++ + ": " + dp[cur][0] / 2);
            }
        }
    }
}
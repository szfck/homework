import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int m = Integer.parseInt(br.readLine());
            if (m == 0) break;
            int n = Integer.parseInt(br.readLine());
            int[] a = new int[n + 2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            a[0] = 0;
            a[n + 1] = m;

            int[][] dp = new int[n + 2][n + 2];
            for (int i = 0; i <= n; i++) {
                dp[i][i + 1] = 0;
            }
            for (int len = 2; len <= n + 1; len++) {
                for (int i = 0; i + len <= n + 1; i++) {
                    int j = i + len;
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                    dp[i][j] += a[j] - a[i];
                }
            }
            System.out.println("The minimum cutting is " + dp[0][n + 1] + ".");

        }
    }


}
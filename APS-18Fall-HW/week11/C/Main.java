import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cas = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            System.out.printf("Case %d:%n", cas++);
            int[] count = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                int x = Integer.parseInt(st.nextToken());
                count[x]++;
            }
            long[][] dp = new long[n + 1][n + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j <= n; j++) {
                    for (int k = 0; k <= count[i]; k++) {
                        if (j >= k) {
                            dp[i][j] += dp[i - 1][j - k];
                        }
                    }
                }
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int q = Integer.parseInt(st.nextToken());
                System.out.printf("%d%n", dp[n][q]);
            }
        }
    }
}

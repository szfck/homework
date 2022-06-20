import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        if (n > m) {
            System.out.println("YES");
            return;
        }

        boolean[][] dp = new boolean[2][m];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[cur ^ 1][j] = dp[cur][j];
            }
            int add = a[i] % m;
            for (int j = 0; j < m; j++) {
                if (dp[cur][j]) {
                    dp[cur ^ 1][(j + add) % m] = true;
                }
            }
            dp[cur ^ 1][add] = true;
            cur ^= 1;
        }
        System.out.println((dp[cur][0] ? "YES" : "NO"));
    }
}

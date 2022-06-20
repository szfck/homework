import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MOD = 1000_000_000 + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] edge = new int[c][c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                edge[i][j] = -1;
            }
        }
        for (int i = 0; i < m; i++) {
            char[] s = st.nextToken().toCharArray();
            int u = s[0] - 'a', v = s[1] - 'a';
            edge[u][v] = i;
        }

        int[][][] dp = new int[n][c][1 << m];
        for (int i = 0; i < c; i++) {
            dp[0][i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < c; j++) {
                for (int t = 0; t < c; t++) {
                    for (int x = 0; x < (1 << m); x++) {
                        int nx = x;
                        if (edge[j][t] > -1) {
                            nx |= (1 << edge[j][t]);
                        }
                        dp[i][t][nx] += dp[i - 1][j][x];
                        dp[i][t][nx] %= MOD;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < (1 << m); i++) {
            int cnt = 0;
            for (int j = 0; j < m; j++) {
                if (((i >> j) & 1) == 1) {
                    cnt++;
                }
            }
            if (cnt <= k) {
                for (int j = 0; j < c; j++) {
                    res += dp[n - 1][j][i];
                    res %= MOD;
                }
            }
        }
        System.out.println(res);
    }

}

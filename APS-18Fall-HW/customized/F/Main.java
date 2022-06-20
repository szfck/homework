import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pattern {
        Set<Integer> masks = new HashSet<>();

        public Pattern(int n, int m, int[][] rows) {
            for (int i = 0; i < 4; i++) {
                add(n, m, rows);
                rows = rt(rows);
            }
        }

        private int[][] rt(int[][] rows) {
            int r = rows.length, c = rows[0].length;
            int[][] a = new int[c][r];
            for (int i = 0; i < c; i++) {
                for (int j = 0; j < r; j++) {
                    a[i][j] = rows[r - 1 - j][i];
                }
            }
            return a;
        }

        private void add(int n, int m, int[][] rows) {
            int r = rows.length, c = rows[0].length;
            if (r > n || c > m) return;
            for (int R = 0; R + r <= n; R++) {
                for (int C = 0; C + c <= m; C++) {
                    int[][] g = new int[n][m];
                    for (int i = 0; i < r; i++) {
                        for (int j = 0; j < c; j++) {
                            g[R + i][C + j] = rows[i][j];
                        }
                    }
                    int mask = 0;
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            if (g[i][j] == 1) {
                                mask |= (1 << (i * m + j));
                            }
                        }
                    }
                    masks.add(mask);
                }
            }
        }

    }

    private static void init(int n, int m, Pattern[] patterns) { // 7
        patterns[0] = new Pattern(n, m, new int[][] {{1, 0}, {1, 0}, {1, 1}});
        patterns[1] = new Pattern(n, m, new int[][] {{0, 1}, {0, 1}, {1, 1}});
        patterns[2] = new Pattern(n, m, new int[][] {{1, 1, 0}, {0, 1, 1}});
        patterns[3] = new Pattern(n, m, new int[][] {{0, 1, 1}, {1, 1, 0}});
        patterns[4] = new Pattern(n, m, new int[][] {{1, 1}, {1, 1}});
        patterns[5] = new Pattern(n, m, new int[][] {{0, 1, 0}, {1, 1, 1}});
        patterns[6] = new Pattern(n, m, new int[][] {{1, 1, 1, 1}});
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Pattern[] patterns = new Pattern[7];

        int k = Integer.parseInt(br.readLine());
        int[] types = new int[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            types[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int n, m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int target = 0;
        init(n, m, patterns);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 0) {
                    target |= (1 << (i * m + j));
                }
            }
        }
        int tot = n * m;
        boolean[][] dp = new boolean[k + 1][1 << tot];
        dp[0][0] = true;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < (1 << tot); j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = 0; j < (1 << tot); j++) {
                if (dp[i - 1][j]) {
                    for (int mask : patterns[types[i - 1]].masks) {
                        if ((mask & j) == 0) {
                            dp[i][mask | j] = true;
                        }
                    }
                }
            }
        }
        System.out.println(dp[k][target] ? "true" : "false");
    }

}
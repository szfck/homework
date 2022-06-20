import java.util.*;

import java.io.*;

public class Main {
    private static final int MOD = 1000_000_000 + 7;
    private static boolean check(char ch, int put, int c) {
        return (ch != '.' && put == c) || (ch == '.' && put < c);
    }
    private static boolean allow(int[] colors, int c) {
        int[] count = new int[c];
        for (int color : colors) {
            if (color < c) {
                count[color]++;
                if (count[color] >= 3) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] g = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                g[i][j] = s.charAt(j);
            }
        }
        int[][][] dp = new int[m + 1][c + 1][c + 1]; // color in range [0, c) and c means #
        dp[0][c][c] = 1; // assume there is ## before first column
        for (int i = 1; i <= m; i++) {
            for (int c1 = 0; c1 <= c; c1++) {
                if (!check(g[0][i - 1], c1, c)) continue;
                for (int c2 = 0; c2 <= c; c2++) {
                    if (!check(g[1][i - 1], c2, c)) continue;
                    for (int pc1 = 0; pc1 <= c; pc1++) {
                        for (int pc2 = 0; pc2 <= c; pc2++) {
                            if (allow(new int[]{pc1, pc2, c1, c2}, c)) {
                                dp[i][c1][c2] = (dp[i][c1][c2] + dp[i - 1][pc1][pc2]) % MOD;
                            }
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i <= c; i++) {
            for (int j = 0; j <= c; j++) {
            res = (res + dp[m][i][j]) % MOD;
            }
        }
        System.out.println(res);
    }
}
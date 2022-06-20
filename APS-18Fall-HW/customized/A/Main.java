import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] g = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                g[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int tot = n * m;
        int[] mask = new int[1 << tot];
        boolean[] vis = new boolean[1 << tot];
        mask[(1 << tot) - 1] = 0;
        vis[(1 << tot) - 1] = true;
        System.out.println(dfs(0, tot, mask, vis, g, n, m, true));
    }

    private static int dfs(int x, int tot, int[] mask, boolean[] vis, int[][] g, int n, int m, boolean max) {
        if (vis[x]) return mask[x];
        mask[x] = max ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < tot; i++) {
            if (!check(x, i)) {
                int pre = dfs(x | (1 << i), tot, mask, vis, g, n, m, !max);
                int r = i / m, c = i % m;
                int val = g[r][c];
                if (c > 0 && check(x, i - 1)) val += g[r][c - 1];
                if (c < m - 1 && check(x, i + 1)) val += g[r][c + 1];
                if (r > 0 && check(x, i - m)) val += g[r - 1][c];
                if (r < n - 1 && check(x, i + m)) val += g[r + 1][c];
                if (max) {
                    mask[x] = Math.max(mask[x], pre + val);
                } else {
                    mask[x] = Math.min(mask[x], pre - val);
                }
            }
        }
        vis[x] = true;
        return mask[x];
    }

    private static boolean check(int x, int i) {
        return ((x >> i) & 1) == 1;
    }


}

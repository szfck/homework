import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            boolean[][] vis = new boolean[m][n];
            char[][] g = new char[m][n];
            for (int i = 0; i < m; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) {
                    g[i][j] = s.charAt(j);
                }
            }
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            char land = g[x][y];

            dfs(x, y, m, n, vis, g, land);

            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res = Math.max(res, dfs(i, j, m, n, vis, g, land));
                }
            }
            System.out.println(res);
            br.readLine();
        }
    }

    private static int dfs(int x, int y, int m, int n, boolean[][] vis, char[][] g, char land) {
        if (x < 0 || x >= m || y < 0 || y >= n) return 0;
        if (vis[x][y]) return 0;
        if (g[x][y] != land) return 0;
        vis[x][y] = true;
        int count = 1;
        count += dfs(x, (y + n - 1) % n, m, n, vis, g, land);
        count += dfs(x, (y + 1) % n, m, n, vis, g, land);
        count += dfs(x - 1, y, m, n, vis, g, land);
        count += dfs(x + 1, y, m, n, vis, g, land);
        return count;
    }

}
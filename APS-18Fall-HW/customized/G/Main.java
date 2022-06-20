
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static final int[][] dir = {{-1, 0},{0, 1}, {0, -1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        char[][] g = new char[m][m];
        int sx = -1, sy = -1, ex = -1, ey = -1;
        for (int i = 0; i < m; i++) {
            g[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 'r') {
                    sx = i; sy = j;
                } else if (g[i][j] == 'd') {
                    ex = i; ey = j;
                }
            }
        }
        // false if it's s or non n-station k-dis nearby(except destination)
        boolean[][] visible = genVisible(m, n, k, ex, ey, g);
        // go everywhere except s
        int[][] zombieReach = genZombieReach(m, g);
//        print(visible);
//        print(zombieReach);
        int minDis = bfs(sx, sy, ex, ey, m, visible, zombieReach);
        if (minDis == -1) {
            System.out.println("impossible");
        } else {
            genMinPath(sx, sy, ex, ey, m, visible, zombieReach, minDis);
        }
    }
    static void print(boolean[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void print(int[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list[i].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void genMinPath(int sx, int sy, int ex, int ey, int m, boolean[][] visible, int[][] zombieReach, int minDis) {
        int[][] dis = new int[m][m];
        int[][] pre = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], -1);
            Arrays.fill(pre[i], -1);
        }
        dis[ex][ey] = minDis;
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(ex, ey));
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int x = cur.x, y = cur.y;
            for (int k = 0; k < 4; k++) {
                int dx = x + dir[k][0];
                int dy = y + dir[k][1];
                if (dx < 0 || dx >= m || dy < 0 || dy >= m || !visible[dx][dy] || dis[x][y] - 1 > zombieReach[dx][dy]) continue;
                if (dis[dx][dy] == -1) {
                    que.add(new Pair(dx, dy));
                    dis[dx][dy] = dis[x][y] - 1;
                }
                if (dis[dx][dy] == dis[x][y] - 1) {
                    if (pre[dx][dy] == -1 || pre[dx][dy] > k) {
                        pre[dx][dy] = k;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        String dirStr = "trlb";
        while (sx != ex || sy != ey) {
            int next = 3 - pre[sx][sy];
            sb.append(dirStr.charAt(next));
            sx += dir[next][0];
            sy += dir[next][1];
        }
        System.out.println(sb);
    }

    private static int bfs(int sx, int sy, int ex, int ey, int m, boolean[][] visible, int[][] zombieReach) {
        if (!visible[sx][sy]) return -1;
        int[][] dis = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], -1);
        }
        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(sx, sy));
        dis[sx][sy] = 0;
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int x = cur.x, y = cur.y;
            for (int k = 0; k < 4; k++) {
                int dx = x + dir[k][0];
                int dy = y + dir[k][1];
                if (dx < 0 || dx >= m || dy < 0 || dy >= m || !visible[dx][dy] || dis[x][y] + 1 > zombieReach[dx][dy] || dis[dx][dy] > -1) continue;
                dis[dx][dy] = dis[x][y] + 1;
                que.add(new Pair(dx, dy));
            }
        }
        return dis[ex][ey];
    }

    static class Pair {
        Integer x, y;

        public Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int[][] genZombieReach(int m, char[][] g) {
        int[][] dis = new int[m][m];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        Queue<Pair> que = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 'z') {
                    dis[i][j] = 0;
                    que.add(new Pair(i, j));
                }
            }
        }
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int x = cur.x, y = cur.y;
            for (int k = 0; k < 4; k++) {
                int dx = x + dir[k][0];
                int dy = y + dir[k][1];
                if (dx < 0 || dx >= m || dy < 0 || dy >= m || g[dx][dy] == 's' || dis[dx][dy] != Integer.MAX_VALUE) continue;
                dis[dx][dy] = dis[x][y] + 1;
                que.add(new Pair(dx, dy));
            }
        }
        return dis;
    }

    private static boolean[][] genVisible(int m, int n, int k, int ex, int ey, char[][] g) {
        boolean[][] visible = new boolean[m][m];
        int[][] station = new int[m + 1][m + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                station[i][j] = station[i - 1][j] + station[i][j - 1] - station[i - 1][j - 1];
                if (g[i - 1][j - 1] == 's') {
                    station[i][j] += 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int lx = Math.max(i - k, 0) + 1, ly = Math.max(j - k, 0) + 1;
                int rx = Math.min(i + k, m - 1) + 1, ry = Math.min(j + k, m - 1) + 1;
                int count = station[rx][ry] - station[rx][ly - 1] - station[lx - 1][ry] + station[lx - 1][ly - 1];
                if (i == ex && j == ey) {
                    visible[i][j] = true;
                } else {
                    visible[i][j] = g[i][j] != 's' && count >= n;
                }
            }
        }
        return visible;
    }

}
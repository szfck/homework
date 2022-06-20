import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int r, c, p;
        long dis;

        public Node(int r, int c, int p, long dis) {
            this.r = r;
            this.c = c;
            this.p = p;
            this.dis = dis;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (r != node.r) return false;
            if (c != node.c) return false;
            return p == node.p;
        }

        @Override
        public int hashCode() {
            int result = r;
            result = 31 * result + c;
            result = 31 * result + p;
            return result;
        }

        @Override
        public int compareTo(Node o) {
            return dis == o.dis ? 0 : dis < o.dis ? -1 : 1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] d = new int[n][m];
        int[][] p = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                d[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                p[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Node start = new Node(0, 0, 0, 0);
        pq.add(start);
        long res = -1;
//        Map<Node, Long> map = new HashMap<>();
//        map.put(start, 0L);
        long[][][] dist = new long[n][m][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int t = 0; t <= k; t++) {
                    dist[i][j][t] = Long.MAX_VALUE;
                }
            }
        }
        dist[0][0][0] = 0;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int r = cur.r, c = cur.c;
            if (r == n - 1 && c == m - 1) {
                res = cur.dis;
                break;
            }
            if (dist[r][c][cur.p] < cur.dis) continue;
//            if (map.get(cur) < cur.dis) continue;
            for (int t = 0; t < 4; t++) {
                int nr = r + dir[t][0];
                int nc = c + dir[t][1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    long nd = cur.dis + d[nr][nc];
                    int np = cur.p + p[nr][nc];
                    if (np > k) continue;
                    if (nd < dist[nr][nc][np]) {
                        Node next = new Node(nr, nc, np, nd);
                        dist[nr][nc][np] = nd;
                        pq.add(next);
                    }
//                    if (!map.containsKey(next) || map.get(next) > nd) {
//                        map.put(next, nd);
//                        pq.add(next);
//                    }
                }
            }
        }
        System.out.println(res);
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int to, dis;

        public Edge(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }
    }

    static class Node implements Comparable<Node> {
        Integer id, vis, take;
        Long dis;

        public Node(Integer id, Integer vis, Integer take, Long dis) {
            this.id = id;
            this.vis = vis;
            this.take = take;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis.compareTo(o.dis);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            u--;
            v--;
            g.get(u).add(new Edge(v, d));
            g.get(v).add(new Edge(u, d));
        }
        int[] pick = new int[n];
        int[] drop = new int[n];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            pick[u - 1] |= 1 << i;
            drop[v - 1] |= 1 << i;
        }
        long[][][] dist = new long[n][1 << p][1 << p];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < (1 << p); j++) {
                for (int t = 0; t < (1 << p); t++) {
                    dist[i][j][t] = Integer.MAX_VALUE;
                }
            }
        }
        List<List<Integer>> subset = new ArrayList<>();
        for (int i = 0; i < (1 << p); i++) {
            subset.add(new ArrayList<>());
        }
        for (int i = 0; i < (1 << p); i++) {
            for (int j = 0; j < (1 << p); j++) { // j contains i
                if ((i & j) == i) {
                    subset.get(j).add(i);
                }
            }
        }
        int[] bits = new int[1 << p];
        for (int i = 0; i < (1 << p); i++) {
            int cnt = 0;
            for (int j = 0; j < p; j++) {
                if (((i >> j) & 1) == 1) {
                    cnt++;
                }
            }
            bits[i] = cnt;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long res = -1;
        for (int mask : subset.get(pick[0])) {
            add(0, 0, 0, mask, drop[0], 0, pq, dist, q, bits);
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.vis == (1 << p) - 1) {
                res = cur.dis;
                break;
            }
            if (cur.dis > dist[cur.id][cur.vis][cur.take]) continue;
            int u = cur.id;
            for (Edge edge : g.get(u)) {
                int v = edge.to;
                int vis = cur.vis;
                int take = cur.take;
                int need = pick[v] ^ ((take | vis) & pick[v]);
                for (int mask : subset.get(need)) {
                    add(v, vis, take, mask, drop[v], cur.dis + edge.dis, pq, dist, q, bits);
                }
            }
        }
        System.out.println(res);
    }

    private static void add(int v, int vis, int take, int pick, int drop, long dis, PriorityQueue<Node> pq,
                            long[][][] dist, int q, int[] bits) {
        take |= pick;
        int putdown = take & drop;
        vis |= putdown;
        take ^= putdown;
        if (bits[take] <= q && dis < dist[v][vis][take]) {
//            System.out.printf("add vis:%d, take:%d, dis:%d%n", vis, take, dis);
            dist[v][vis][take] = dis;
            pq.add(new Node(v, vis, take, dis));
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int to, d, c;

        public Edge(int to, int d, int c) {
            this.to = to;
            this.d = d;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            char c = st.nextToken().toCharArray()[0];
            u--;
            v--;
            g.get(u).add(new Edge(v, d, c - 'a'));
            g.get(v).add(new Edge(u, d, c - 'a'));
        }
        int initColor = 0;
        if (check(initColor, k + 1, k, g, n)) {
            System.out.println("relaxing");
        } else {
            int l = 1, r = k;
            int res = k + 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (check(initColor, mid, k, g, n)) {
                    res = mid;
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (res == k + 1) {
                System.out.println("impossible");
            } else {
                System.out.println(res);
            }
        }
    }

    static class Node implements Comparable<Node> {
        Integer color, id, dis;

        public Node(Integer color, Integer id, Integer dis) {
            this.color = color;
            this.id = id;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return dis.compareTo(o.dis);
        }
    }

    static class Pair implements Comparable<Pair> {
        Integer color, id;

        public Pair(Integer color, Integer id) {
            this.color = color;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (color != null ? !color.equals(pair.color) : pair.color != null) return false;
            return id != null ? id.equals(pair.id) : pair.id == null;
        }

        @Override
        public int compareTo(Pair o) {
            int res = color.compareTo(o.color);
            if (res != 0) return res;
            res = id.compareTo(o.id);
            return res;
        }

        @Override
        public int hashCode() {
            int result = color != null ? color.hashCode() : 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }
    }

    private static boolean check(int initColor, int t, int k, List<List<Edge>> g, int n) {
        Map<Pair, Integer> dist = new TreeMap<>();
//        int[][] dist = new int[5][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < 5; j++) {
//                dist[j][i] = k + 1;
//            }
//        }
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
//        dist[initColor][0] = 0;
        dist.put(new Pair(initColor, 0), 0);
        pq.offer(new Node(initColor, 0, 0));
        int res = k + 1;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int c = cur.color, id = cur.id;
            if (dist.get(new Pair(c, id)) < cur.dis) continue;
//            if (dist[c][id] < cur.dis) continue;
            if (id == n - 1) {
                res = Math.min(res, cur.dis);
                continue;
            }
            if (cur.dis > k) continue;
            for (Edge edge : g.get(cur.id)) {
                int cost = edge.d;
                if (c != edge.c) {
                    cost += t;
                }
                Pair pair = new Pair(edge.c, edge.to);
                if (!dist.containsKey(pair) || dist.get(pair) > cur.dis + cost) {
                    dist.put(pair, cur.dis + cost);
                    pq.offer(new Node(edge.c, edge.to, dist.get(pair)));
                }
//                if (dist[edge.c][edge.to] > cur.dis + cost) {
//                    dist[edge.c][edge.to] = cur.dis + cost;
//                    pq.offer(new Node(edge.c, edge.to, dist[edge.c][edge.to]));
//                }
            }
        }
        return res <= k;
    }
}

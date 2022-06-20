import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    private static final int INF = 0x3f3f3f3f;

    static class Edge {
        Integer to;
        Integer cost;

        public Edge(Integer to, Integer cost) {
            this.to = to;
            this.cost = cost;
        }

    }

    static class Node implements Comparable<Node> {
        Integer id, d;

        public Node(Integer id, Integer d) {
            this.id = id;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return d.compareTo(o.d);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for (int cas = 1; cas <= cases; cas++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            List<List<Edge>> g = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                g.get(u).add(new Edge(v, w));
                g.get(v).add(new Edge(u, w));
            }
            int dis = dijkstra(s, t, n, g);
            System.out.printf("Case #%d: ", cas);
            if (dis == INF) {
                System.out.println("unreachable");
            } else {
                System.out.println(dis);
            }
        }
    }

    private static int dijkstra(int s, int t, int n, List<List<Edge>> g) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = INF;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.offer(new Node(s, 0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.id;
            if (u == t) return dist[t];
            if (cur.d > dist[u]) continue;
            for (Edge edge : g.get(u)) {
                int v = edge.to, w = edge.cost;
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new Node(v, dist[v]));
                }
            }
        }
        return dist[t];
    }

}
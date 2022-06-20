import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static class Edge {
        int to, d;

        public Edge(int to, int d) {
            this.to = to;
            this.d = d;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Edge>> g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            g.get(u).add(new Edge(v, d));
            g.get(v).add(new Edge(u, d));
        }
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int[] pos = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
           pos[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        if (check(n, 1000000, t, pos, g) == -1) {
            System.out.println("impossible");
        } else {
            double l = 0, r = 1000000;
            for (int i = 0; i < 100; i++) {
                double mid = (l + r) / 2;
                if (check(n, mid, t, pos, g) == 1) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            double speed = (l + r) / 2 * 60;
            System.out.printf("%.12f%n", speed);
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        Double dis;

        @Override
        public int compareTo(Node o) {
            return dis.compareTo(o.dis);
        }

        public Node(int id, Double dis) {
            this.id = id;
            this.dis = dis;
        }
    }

    private static int check(int n, double speed, int t, int[] pos, List<List<Edge>> g) { // speed : miles per minute
        PriorityQueue<Node> pq = new PriorityQueue<>();
        double[] dist = new double[n];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0;
        pq.add(new Node(0, 0.0));
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int u = cur.id;
            if (cur.dis > dist[u]) continue;
            for (Edge edge : g.get(u)) {
                int v = edge.to;
                if (edge.d != -1 && !reach(cur.dis + 1, speed, edge.d)) continue;
                double ndis = cur.dis + 1;
                if (ndis < dist[v]) {
                    dist[v] = ndis;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
        boolean ok = true;
        for (int p : pos) {
            if (dist[p] == Double.MAX_VALUE) return -1;
            if (dist[p] / speed > t) ok = false;
        }
        return ok ? 1 : 0;
    }

    private static boolean reach(double dis, double speed, int d) {
        return dis <= d * speed;
    }

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            List<List<Integer>> g = new ArrayList<>();
            int[] deg = new int[n];
            for (int i = 0; i < n; i++) {
                g.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                u--;
                v--;
                g.get(u).add(v);
                deg[v]++;
            }
            List<Integer> order = new ArrayList<>();
            Queue<Integer> que = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (deg[i] == 0) {
                    que.add(i);
                }
            }
            while (!que.isEmpty()) {
                int u = que.poll();
                order.add(u);
                for (int v : g.get(u)) {
                    if (--deg[v] == 0) {
                        que.add(v);
                    }
                }
            }
            if (order.size() != n) {
                System.out.println("IMPOSSIBLE");
            } else {
                for (int v : order) {
                    System.out.println(v + 1);
                }
            }
        }
    }
}
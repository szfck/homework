import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] inq;
    private static Stack<Integer> stk;
    private static int[] color;
    private static int colCnt;
    private static int[] vnum;
    private static int[] vlow;
    private static int T;
    private static List<List<Integer>> g;

    private static void dfs(int u, int d) {
        if (u == 200000) return;
        dfs(u+1, d+1);
    }

    public static void main(String[] args) throws IOException {
        dfs(0,0);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        g = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            g.add(new ArrayList<>());
//        }
//        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine());
//            int u = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//            u--;
//            v--;
//            g.get(u).add(v);
//        }
//        colCnt = 0;
//        inq = new int[n];
//        color = new int[n];
//        vnum = new int[n];
//        vlow = new int[n];
//        stk = new Stack<>();
//        T = 0;
//        for (int i = 0; i < n; i++) {
//            if (inq[i] == 0) {
//                dfs(i);
//            }
//        }
//        int[] deg = new int[colCnt];
//        int[] minIndex = new int[colCnt];
//        for (int i = 0; i < colCnt; i++) {
//            minIndex[i] = Integer.MAX_VALUE;
//        }
//        for (int i = 0; i < n; i++) {
//            minIndex[color[i]] = Math.min(i, minIndex[color[i]]);
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j : g.get(i)) {
//                int u = color[i], v = color[j];
//                if (u != v) {
//                    deg[v]++;
//                }
//            }
//        }
//        List<Integer> res = new ArrayList<>();
//        for (int i = 0; i < colCnt; i++) {
//            if (deg[i] == 0) {
//                res.add(minIndex[i] + 1);
//            }
//        }
//        System.out.println(res.size());
//        for (int index : res) {
//            System.out.print(index + " ");
//        }
//        System.out.println();
    }

    private static void dfs(int u) {
        vnum[u] = vlow[u] = T++;
        inq[u] = 1;
        stk.add(u);
        for (int v : g.get(u)) {
            if (inq[v] == 0) {
                dfs(v);
                vlow[u] = Math.min(vlow[u], vlow[v]);
            } else if (inq[v] == 1) {
                vlow[u] = Math.min(vlow[u], vnum[v]);
            }
        }
        int cur = -1;
        if (vlow[u] == vnum[u]) {
            do {
                cur = stk.pop();
                color[cur] = colCnt;
                inq[cur] = 2;
            } while (cur != u);
            colCnt++;
        }
    }
}

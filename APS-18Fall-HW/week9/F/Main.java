import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    private static int[] color;
    private static int[] vnum;
    private static int[] vlow;
    private static int[] inq;
    private static Stack<Integer> stk;
    private static int colorCnt;
    private static List<List<Integer>> g;
    private static int T;

    private static void dfs(int x, int father) {
        vnum[x] = vlow[x] = T++;
        stk.add(x);
        inq[x] = 1;
        for (int v : g.get(x)) {
            if (v == father) continue;
            if (inq[v] == 0) {
                System.out.println((x + 1) + " " + (v + 1));
                dfs(v, x);
                vlow[x] = Math.min(vlow[x], vlow[v]);
                if (vnum[x] < vlow[v]) {
                    System.out.println((v + 1) + " " + (x + 1));
                }
            } else {
                if (inq[v] == 1) {
                    if (vnum[v] < vnum[x]) {
                        System.out.println((x + 1) + " " + (v + 1));
                    }
                    vlow[x] = Math.min(vlow[x], vnum[v]);
                }
            }
        }
        if (vlow[x] == vnum[x]) {
            int top = -1;
            do {
                top = stk.pop();
                color[top] = colorCnt;
                inq[top] = 2;
            } while (top != x);
            colorCnt++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cas = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            System.out.println(cas++);
            System.out.println();
            g = new ArrayList<>();
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
                g.get(v).add(u);
            }
            color = new int[n];
            vnum = new int[n];
            vlow = new int[n];
            inq = new int[n];
            stk = new Stack<>();
            colorCnt = 0;
            T = 0;
            for (int i = 0; i < n; i++) {
                if (inq[i] == 0) {
                    dfs(i, -1);
                }
            }
            System.out.println('#');
        }
    }

}
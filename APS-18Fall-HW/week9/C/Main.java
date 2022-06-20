import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static class Node {
        int value, step;

        public Node(int value, int step) {
            this.value = value;
            this.step = step;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cas = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int U = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            if (L == 0 && U == 0 && R == 0) break;
            List<Integer> arr = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < R; i++) {
                int val = Integer.parseInt(st.nextToken());
                arr.add(val);
            }
            Queue<Node> que = new LinkedList<>();
            boolean[] vis = new boolean[10000];
            que.add(new Node(L, 0));
            vis[L] = true;
            int res = -1;
            while (!que.isEmpty()) {
                Node cur = que.poll();
                if (cur.value == U) {
                    res = cur.step;
                    break;
                }
                for (Integer v : arr) {
                    int val = (cur.value + v) % 10000;
                    if (!vis[val]) {
                        vis[val] = true;
                        que.add(new Node(val, cur.step + 1));
                    }
                }
            }
            System.out.printf("Case %d: ", cas++);
            if (res == -1) {
                System.out.println("Permanently Locked");
            } else {
                System.out.println(res);
            }
        }
    }

}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static class Circle {
        Integer x, y, r;

        public Circle(Integer x, Integer y, Integer r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Circle[] circles = new Circle[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            circles[i] = new Circle(x, y, r);
        }
        for (int k = 0; k < n; k++) {
            boolean reach = true;
            boolean[] vis = new boolean[n];
            for (int i = 0; i <= k; i++) {
                if (!vis[i] && containsX(circles[i], 0)) {
                    if (dfs(circles, n, i, vis, k)) {
                        reach = false;
                        break;
                    }
                }
            }
            if (!reach) {
                System.out.println(k);
                break;
            }
        }
    }

    private static boolean containsX(Circle circle, int x) {
        int left = circle.x - circle.r, right = circle.x + circle.r;
        return left <= x && x <= right;
    }

    private static boolean connect(Circle a, Circle b) {
        int dx = a.x - b.x, dy = a.y - b.y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist <= a.r + b.r;
    }

    private static boolean dfs(Circle[] circles, int n, int id, boolean[] vis, int k) {
        vis[id] = true;
        if (containsX(circles[id], 200)) return true;
        for (int i = 0; i <= k; i++) {
            if (!vis[i] && connect(circles[i], circles[id])) {
                if (dfs(circles, n, i, vis, k)) {
                    return true;
                }
            }
        }
        return false;
    }


}
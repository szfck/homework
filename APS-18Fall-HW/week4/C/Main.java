import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static int find(int[] f, int x) {
        if (x == f[x]) return x;
        return f[x] = find(f, f[x]);
    }
    public static void union(int[] f, int x, int y) {
        int fx = find(f, x);
        int fy = find(f, y);
        if (fx != fy) {
            f[fx] = fy;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cas = 1;
        while (true) {
            int n = in.nextInt(), m = in.nextInt();
            if (n == 0 && m == 0) break;
            int[] f = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                f[i] = i;
            }
            for (int i = 0; i < m; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                union(f, x, y);
            }
            int res = 0;
            for (int i = 1; i <= n; i++) {
                if (find(f, i) == i) {
                    res++;
                }
            }
            System.out.println("Case " + cas++ + ": " + res);
        }
    }
}
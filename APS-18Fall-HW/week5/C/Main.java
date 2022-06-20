import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static double min;
    public static int[] minres;
    public static void dfs(int id, int count, int[][] g, int[] res) {
        if (id == 25 || count == 5) {
            if (count == 5) {
                double sum = 0;
                for (int i = 0; i < 25; i++) {
                    double tmpmin = Double.MAX_VALUE;
                    for (int v : res) {
                        tmpmin = Math.min(tmpmin, (Math.abs(v / 5 - i / 5) + Math.abs(v % 5 - i % 5)));
                    }
                    sum += tmpmin * g[i / 5][i % 5];
                }
                if (sum < min) {
                    min = sum;
                    minres = Arrays.copyOf(res, 5);
                }
            }
            return;
        }
        res[count] = id;
        dfs(id + 1, count + 1, g, res);
        dfs(id + 1, count, g, res);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            int[][] g = new int[5][5];
            for (int i = 0; i < n; i++) {
                int r = in.nextInt(), c = in.nextInt();
                int num = in.nextInt();
                g[r][c] = num;
            }
            min = Double.MAX_VALUE;
            dfs(0, 0, g, new int[5]);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                if (i > 0) sb.append(' ');
                sb.append(minres[i]);
            }
            System.out.println(sb);
        }
    }
}
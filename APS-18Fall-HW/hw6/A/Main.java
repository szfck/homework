import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        while (in.hasNext()) {
            solver.solve(in, out);
        }
        out.close();
    }

    static class Task {
        int best_num;
        int best_count;
        public void dfs(int r, int c, int pr, int[] pc, int num, int n, char[][] g) {
            if (r == n) {
                if (num > best_num) {
                    best_num = num;
                    best_count = 1;
                } else if (num == best_num) {
                    best_count++;
                }
                return;
            }
            int nr = r, nc = c + 1;
            if (nc == n) {
                nr++;
                nc = 0;
            }

            {
                int tpc = pc[c];
                if (g[r][c] == '#') pc[c] = 0;
                dfs(nr, nc, nc == 0 ? 0 : (g[r][c] == '.' ? pr : 0), pc, num, n, g);
                pc[c] = tpc;
            }

            boolean could_put = (pr == 0) && (pc[c] == 0) && (g[r][c] == '.');
            if (could_put) {
                int tpc = pc[c];
                pc[c] = 1;
                dfs(nr, nc, nc == 0 ? 0 : 1, pc, num + 1, n, g);
                pc[c] = tpc;
            }
        }
        public void solve(Scanner in, PrintWriter out) {
            int n = in.nextInt();
            char[][] g = new char[n][n];
            for (int i = 0; i < n; i++) {
                String s = in.next();
                for (int j = 0; j < n; j++) {
                    g[i][j] = s.charAt(j); 
                }
            }
            int[] col = new int[n];
            best_num = 0;
            best_count = 0;
            dfs(0, 0, 0, col, 0, n, g);
            System.out.println(best_num + " " + best_count);
        }
    }
}

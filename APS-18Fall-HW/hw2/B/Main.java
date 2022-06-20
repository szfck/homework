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
        public void solve(Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int[][] ds = new int[n][3];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 3; j++) {
                    ds[i][j] = in.nextInt();
                }
            }
            int ma = -1;
            int a = 0, b = -1, c = -1;
            for (int j = 0; j < n; j++) {
                for (int z = 0; z < n; z++) {
                    int sum = 0;
                    for (int k = 0; k < n; k++) {
                        int num = (ds[k][0]) * 100 + (ds[(k + j) % n][1]) * 10 + (ds[(k + z) % n][2]);
                        sum += num * num;
                    }
                    if (sum > ma) {
                        ma = sum;
                        b = j;
                        c = z;
                    }
                }
            }
            out.println(ma);
            out.println(a + " " + b + " " + c);
        }
    }
}

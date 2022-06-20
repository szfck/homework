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
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            long res = 0;
            for (int i = n - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (a[j] > a[j + 1]) {
                        res += a[j] * a[j + 1];
                        int tmp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = tmp;
                    }
                }
            }
            out.println(res);
        }
    }
}

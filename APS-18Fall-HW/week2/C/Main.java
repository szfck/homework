import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Task task = new Task();
        while (in.hasNext()) {
            task.solve(in, out);
            out.flush();
        }
        out.close();
    }
    static class Task {
        public void solve(Scanner in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            Arrays.sort(a);
            StringBuilder sb = new StringBuilder();
            for (int v : a) {
                sb.append(v + " ");
            }
            out.println(sb);
        }
    }
}

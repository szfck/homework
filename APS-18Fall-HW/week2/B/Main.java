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
            String[] s = in.next().split("\\+");
            Arrays.sort(s);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length; i++) {
                if (i > 0) sb.append("+");
                sb.append(s[i]);
            }
            out.println(sb);
        }
    }
}

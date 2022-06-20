import java.util.Scanner;
import java.io.PrintWriter;
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
            int a = in.nextInt(), b = in.nextInt();
            out.println((a * b) % 2 == 0 ? "Even" : "Odd");
        }
    }
}

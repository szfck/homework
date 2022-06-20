import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
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
            Node[] a = new Node[n];
            for (int i = 0; i < n; i++) {
                int p = in.nextInt(), q = in.nextInt();
                a[i] = new Node(p, q);
            }
            Arrays.sort(a, new Comparator<Node>() {
                public int compare(Node x, Node y) {
                    if (x.price != y.price) {
                        return x.price - y.price;
                    }
                    return x.quality - y.quality;
                }
            });
            boolean flag = false;
            for (int i = 0; i < a.length - 1; i++) {
                if (a[i].quality > a[i + 1].quality) {
                    flag = true;
                    break;
                }
            }
            out.println(flag ? "Happy Alex" : "Poor Alex");
        }
    }

    static class Node {
        int price, quality;
        Node(int price, int quality) {
            this.price = price;
            this.quality = quality;
        }
    }
}

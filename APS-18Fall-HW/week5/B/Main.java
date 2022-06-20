import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static void dfs(int[] a, int pos, int n, int[] b, int cnt) {
        if (cnt == 6) {
            String tmp = "";
            for (int i = 0; i < 6; i++) {
                if (i > 0) tmp += " ";
                tmp += b[i];
            }
            System.out.println(tmp);
            return;
        }
        b[cnt] = a[pos];
        dfs(a, pos + 1, n, b, cnt + 1);
        if (n - pos - 1 >= 6 - cnt) {
            dfs(a, pos + 1, n, b, cnt);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean first = true;
        while (true) {
            int n = in.nextInt();
            if (n == 0) break;
            if (!first) {
                System.out.println();
            }
            first = false;
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int[] b = new int[6];
            dfs(a, 0, n, b, 0);
        }
    }
}
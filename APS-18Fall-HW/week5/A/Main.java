import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Main {
    public static int bsearch(List<Integer> a, int sum) {
        int res = a.size();
        int l = 0, r = a.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (a.get(m) >= sum) {
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        if (res == a.size()) return a.get(a.size() - 1);
        if (res > 0 && sum - a.get(res - 1) <= a.get(res) - sum) return a.get(res - 1);
        return a.get(res);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int cas = 1;
        while (true) {
            int n = in.nextInt();
            if (n == 0) break;
            System.out.printf("Case %s:\n", cas++);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            List<Integer> b = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        b.add(a[i] + a[j]);
                    }
                }
            }
            Collections.sort(b);
            int m = in.nextInt();
            for (int i = 0; i < m; i++) {
                int sum = in.nextInt();
                System.out.printf("Closest sum to %d is %d.\n", sum, bsearch(b, sum));
            }
        }
    }
}
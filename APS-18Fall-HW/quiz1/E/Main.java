import java.io.*;
import java.util.*;

public class Main {
    public static final int LEF = -1;
    public static final int RIG = -2;
    public static final long MOD = 1000000007;
    static void dfs(int l, int r, int[] a, StringBuilder sb) {
        if (l > r) return;
        int val = a[l];
        int lo = l + 1, hi = r;
        int p = r + 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2; 
            if (a[mid] > val) {
                p = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        dfs(l + 1, p - 1, a, sb);
        dfs(p, r, a, sb);
        sb.append(val + " ");
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            StringBuilder sb = new StringBuilder();
            dfs(0, n - 1, a, sb);
            System.out.println(sb);
        }
    }
}
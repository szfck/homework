import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] vis = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            String num = st.nextToken();
            if (num.equals("?")) {
                a[i] = 0;
            } else {
                a[i] = Integer.parseInt(num);
                vis[a[i]] = true;
            }
        }
        int ptr = n;
        for (int i = 0; i < n; i++) {
            if (a[i] > 0) continue;
            while (vis[ptr]) ptr--;
            a[i] = ptr;
            vis[ptr--] = true;
        }
        int[] c = new int[n + 1];
        long res = 0;
        for (int i = 0; i < n; i++) {
            res += sum(c, n) -  sum(c, a[i]);
            add(c, a[i], n);
        }
        System.out.println(res);
    }

    private static void add(int[] c, int x, int n) {
        for (; x <= n; x += x & -x) {
            c[x]++;
        }
    }

    private static long sum(int[] c, int x) {
        long res = 0;
        for (; x > 0; x -= x & -x) {
            res += c[x];
        }
        return res;
    }

}

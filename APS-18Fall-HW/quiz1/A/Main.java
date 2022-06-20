import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                a[i] = in.nextInt();
            }
            a[0] = 0x3f3f3f3f;
            int[] stk = new int[n + 1];
            int st = 1, en = 0;
            int ptr = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (i == n) {
                    sb.append('n');
                    continue;
                }
                while (ptr <= n && ptr <= i + k) {
                    while (st <= en && a[ptr] >= a[stk[en]]) {
                        en--;
                    }
                    stk[++en] = ptr;
                    ptr++;
                }
                while (stk[st] <= i) {
                    st++;
                }
                if (st <= en && a[stk[st]] > a[i]) {
                    sb.append('y');
                } else {
                    sb.append('n');
                }
            }
            System.out.println(sb);
        }       
    }
}
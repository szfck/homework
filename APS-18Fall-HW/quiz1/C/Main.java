import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            HashMap<String, Integer> tim = new HashMap<>();
            String[] a = new String[400010];
            int[] b = new int[400010];
            int st = 1, en = 0;
            int T = 0;
            for (int i = 0; i < n; i++) {
                String s = in.next();
                if (!tim.containsKey(s)) {
                    tim.put(s, T);
                }
                a[++en] = s;
                b[en] = T;
            }
            int q = in.nextInt();
            while (q-- > 0) {
                ++T;
                String op = in.next();
                if (op.charAt(0) == 'j') {
                    String name = in.next();
                    tim.put(name, T);
                    a[++en] = name;
                    b[en] = T;
                } else if (op.charAt(0) == 'l') {
                    String name = in.next();
                    tim.put(name, -1);
                } else if (op.charAt(0) == 's') {
                    while (st <= en && tim.get(a[st]) != b[st]) {
                        st++;
                    }
                    System.out.println(a[st]);
                    st++;
                }
            }
        }       
    }
}
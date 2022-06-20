import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int n = in.nextInt();
        int[] a = new int[n + 2];
        int[] lef = new int[n + 2], rig = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        long res = 0;
        // min
        {
            a[0] = a[n + 1] = Integer.MIN_VALUE;
            Stack<Integer> stk = new Stack<>();
            stk.push(0);
            for (int i = 1; i <= n; i++) {
                while (a[i] < a[stk.peek()]) {
                    stk.pop();
                }
                lef[i] = stk.peek();
                stk.push(i);
            }
            stk.clear();
            stk.push(n + 1);
            for (int i = n; i > 0; i--) {
                while (a[i] <= a[stk.peek()]) {
                    stk.pop();
                }
                rig[i] = stk.peek();
                stk.push(i);
            }
            for (int i = 1; i <= n; i++) {
                res -= (long) (i - lef[i]) * (rig[i] - i) * a[i];
            }
        }
        // max
        {
            a[0] = a[n + 1] = Integer.MAX_VALUE;
            Stack<Integer> stk = new Stack<>();
            stk.push(0);
            for (int i = 1; i <= n; i++) {
                while (a[i] > a[stk.peek()]) {
                    stk.pop();
                }
                lef[i] = stk.peek();
                stk.push(i);
            }
            stk.clear();
            stk.push(n + 1);
            for (int i = n; i > 0; i--) {
                while (a[i] >= a[stk.peek()]) {
                    stk.pop();
                }
                rig[i] = stk.peek();
                stk.push(i);
            }
            for (int i = 1; i <= n; i++) {
                res += (long) (i - lef[i]) * (rig[i] - i) * a[i];
            }
        }
        System.out.println(res);
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
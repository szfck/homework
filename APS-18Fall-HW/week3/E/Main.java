import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        while (true) {
            int n = in.nextInt(), m = in.nextInt();
            if (n == 0 && m == 0) break;
            HashMap<Integer, Integer> lef = new HashMap<>();
            HashMap<Integer, Integer> rig = new HashMap<>();

            StringBuilder sb = new StringBuilder();
            while (m-- > 0) {
                int l = in.nextInt(), r = in.nextInt();
                int L = lef.containsKey(l) ? lef.get(l) : l - 1;
                int R = rig.containsKey(r) ? rig.get(r) : r + 1;
                rig.put(L, R);
                lef.put(R, L);
                String left = L == 0 || L == n + 1 ? "*" : L + "";
                String right =  R == 0 || R == n + 1 ? "*" : R + "";
                sb.append(left + " " + right + "\n");
            }
            sb.append("-");
            System.out.println(sb);
        }
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
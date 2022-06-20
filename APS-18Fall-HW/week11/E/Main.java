import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    static class Pair {
        Integer x, y;

        public Pair(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair hashPair = (Pair) o;

            if (x != null ? !x.equals(hashPair.x) : hashPair.x != null) return false;
            return y != null ? y.equals(hashPair.y) : hashPair.y == null;
        }

        @Override
        public int hashCode() {
            int result = x != null ? x.hashCode() : 0;
            result = 31 * result + (y != null ? y.hashCode() : 0);
            return result;
        }

    }

    static class Hash {
        private static final int[] MOD = new int[] {1000_000_000 + 7, 1000_000_000+ 9};
        private static final int[] A = new int[] {27, 277};
        private static int len;
        private static int[][] fac;
        private static int[][] arr;
        public Hash(String s) {
            this.len = s.length();
            this.fac = new int[2][len + 1];
            this.arr = new int[2][len + 1];
            for (int i = 0; i < 2; i++) {
                init(i, s);
            }
        }

        private void init(int id, String s) {
            fac[id][0] = 1;
            for (int i = 1; i <= len; i++) {
                fac[id][i] = (int) ((1L * fac[id][i - 1] * A[id]) % MOD[id]);
                arr[id][i] = (int) ((1L * arr[id][i - 1] * A[id] + s.charAt(i - 1) - 'A' + 1) % MOD[id]);
            }
        }

        private int get(int id, int i, int j) {
            return (int) (((1L * arr[id][j + 1] - 1L * arr[id][i] * fac[id][j - i + 1] % MOD[id]) + MOD[id]) % MOD[id]);
        }

        public Pair getPair(int i, int j) {
            return new Pair(get(0, i, j), get(1, i, j));
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String s = br.readLine();
            int n = s.length();
            Hash hash = new Hash(s);
            String max = "";
            int cnt = 0;
            for (int len = n; len > 0; len--) {
                Map<Pair, Integer> count = new HashMap<>(); // hash pair count
                Map<Pair, Pair> map = new HashMap<>(); // hash pair to [start, end] pair
                for (int i = 0; i + len <= n; i++) {
                    int j = i + len - 1;
                    Pair pair = new Pair(i, j);
                    Pair hashPair = hash.getPair(i, j);
                    map.put(hashPair, pair);
                    if (!count.containsKey(hashPair)) {
                        count.put(hashPair, 1);
                    } else {
                        count.put(hashPair, count.get(hashPair) + 1);
                    }
                }
                for (Map.Entry<Pair, Integer> entry : count.entrySet()) {
                    if (entry.getValue() >= 2) {
                        Pair hashPair = entry.getKey();
                        Pair pair = map.get(hashPair);
                        String tmp = s.substring(pair.x, pair.y + 1);
                        if (max.equals("") || tmp.compareTo(max) < 0) {
                            max = tmp;
                            cnt = entry.getValue();
                        }
                    }
                }
                if (max.length() > 0) {
                    System.out.printf("%s %d%n", max, cnt);
                    break;
                }
            }
            if (max.equals("")) {
                System.out.println("No repetitions found!");
            }
        }
    }

}
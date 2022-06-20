import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        Scanner in = new Scanner(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        while (in.hasNext()) {
            solver.solve(in, out);
        }
        out.close();
    }

    static class Task {
        int T;
        int getId(char ch) {
            if (ch >= '2' && ch <= '9') return (int) ch - (int) '0' - 2;
            if (ch == 'X') return 8;
            if (ch == 'J') return 9;
            if (ch == 'Q') return 10;
            if (ch == 'K') return 11;
            if (ch == 'A') return 12;
            return -1;
        }

        public void dfs(int pos, String str, int count, int k, char[] cards, char[] s1, char[] s2, ArrayList<String> res, int[] m) {
            if (pos == cards.length) {
                if (count == k) {
                    res.add(str);
                }
                return;
            }
            char ch = cards[pos];
            int id = getId(ch);
            int ma = Math.min(k - count, 4);
            for (int i = 0; i <= ma; i++) {
                if (i > 0) str += ch;

                if (((m[id] >> i) & 1) == 0) {
                    continue;
                }
                dfs(pos + 1, str, count + i, k, cards, s1, s2, res, m);
            }
        }
        public void solve(Scanner in, PrintWriter out) {
            int n = in.nextInt(), k = in.nextInt();
            char[] s1 = in.next().toCharArray(), s2 = in.next().toCharArray();
            char[] cards = "23456789XJQKA".toCharArray();
            int[] m = new int[13];
            int mask = (1 << 5) - 1;
            for (int i = 0; i < 13; i++) {
                m[i] = mask;
            }
            int[] count = new int[13];
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                int id = getId(s1[i]);
                int x = ++count[id];
                if (x > 4) {
                    flag = false;
                    break;
                }
                int tm = 0;
                for (int j = Math.max(x, 2); j < 5; j++) {
                    tm |= (1 << (j - x));
                }
                if (s2[i] == 'y') {
                    m[id] &= tm;
                } else {
                    m[id] &= ((~tm) & mask);
                }
                if (m[id] == 0) {
                    flag = false;
                    break;
                }
            }
            for (int i = 0; i < 13; i++) {
                int lef = (1 << (5 - count[i])) - 1;
                m[i] &= lef;
            }
            if (!flag) {
                System.out.println("impossible");
                return;
            }
            ArrayList<String> res = new ArrayList<>();
            dfs(0, "", 0, k, cards, s1, s2, res, m);
            if (res.size() == 0) {
                System.out.println("impossible");
            } else {
                System.out.println(res.size());
                Collections.reverse(res);
                // for (String str : res) {
                    // System.out.println(str);
                // }
            }
        }
    }
}

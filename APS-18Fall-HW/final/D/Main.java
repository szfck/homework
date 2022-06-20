import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    private static int[][] next;
    private static int n, m;
    private static String[] strs;
    private static Pattern[] patterns;
    private static int res;

    static class Pattern {
        private String p;
        private int[] next;

        public Pattern(String p) {
            int len = p.length();
            this.p = p;
            this.next = new int[len + 1];

            next = new int[len + 1];
            next[0] = next[1] = 0;

            int j = 0;
            for (int i = 2; i <= len; i++) {
                while (j > 0 && p.charAt(j) != p.charAt(i - 1)) {
                    j = next[j];
                }
                if (p.charAt(j) == p.charAt(i - 1)) j++;
                next[i] = j;
            }
        }

        public int count(String s) {
            int res = 0;
            int j = 0;
            for (int i = 0; i < s.length(); i++) {
                while (j > 0 && p.charAt(j) != s.charAt(i)) j = next[j];
                if (p.charAt(j) == s.charAt(i)) j++;
                if (j == p.length()) {
                    res++;
                    j = next[j];
                }
            }
            return res;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        strs = new String[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            strs[i] = st.nextToken();
        }
        st = new StringTokenizer(br.readLine());
        patterns = new Pattern[m];
        next = new int[m][];
        for (int i = 0; i < m; i++) {
            patterns[i] = new Pattern(st.nextToken());
        }
        res = 0;
        dfs(new int[n], new boolean[n], 0);
        System.out.println(res);
    }

    private static void dfs(int[] per, boolean[] vis, int step) {
        if (step == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append(strs[per[i]]);
            }
            int tmp = 0;
            for (int i = 0; i < m; i++) {
                tmp += patterns[i].count(sb.toString());
            }
            res = Math.max(res, tmp);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                per[step] = i;
                dfs(per, vis, step + 1);
                vis[i] = false;
            }
        }
    }

}
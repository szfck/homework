import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        List<List<Integer>> primes = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int id = 0;
        for (int i = 0; i < n; i++) {
            List<Integer> prime = new ArrayList<>();
            int x = Integer.parseInt(st.nextToken());
            for (int j = 2; 1L * j * j <= x; j++) {
                if (x % j == 0) {
                    prime.add(j);
                    if (!map.containsKey(j)) {
                        map.put(j, id++);
                    }
                    while (x % j == 0) {
                        x /= j;
                    }
                }
            }
            if (x > 1) {
                if (!map.containsKey(x)) {
                    map.put(x, id++);
                }
                prime.add(x);
            }
            primes.add(prime);
        }
        int pn = map.size();
        int[] par = new int[n + pn];
        int[] count = new int[n + pn];
        for (int i = 0; i < n + pn; i++) {
            par[i] = i;
            if (i < n) {
                count[i] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j : primes.get(i)) {
                union(par, count, i, n + map.get(j));
            }
        }
        List<Integer> groups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == find(par, i)) {
                groups.add(count[i]);
            }
        }
        Collections.sort(groups);
        int res = 0;
        for (int i = groups.size() - 1; i >= 0 && k > 0; i--,k --) {
            res += groups.get(i);
        }
        System.out.println(res);
    }

    private static void union(int[] par, int[] count, int x, int y) {
        int fx = find(par, x), fy = find(par, y);
        if (fx != fy) {
            if (fx < fy) {
                par[fy] = fx;
                count[fx] += count[fy];
            } else {
                par[fx] = fy;
                count[fy] += count[fx];
            }
        }
    }

    private static int find(int[] par, int x) {
        if (x == par[x]) return x;
        return par[x] = find(par, par[x]);
    }

}
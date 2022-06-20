import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int N = 1000_000;
    private static List<Integer> primes;
    private static boolean[] p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        genPrimes();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            boolean find = false;
            for (int p1 : primes) {
                if (p1 >= n) break;
                if (!p[n - p1]) {
                    System.out.printf("%d = %d + %d%n", n, p1, n - p1);
                    find = true;
                    break;
                }
            }
            if (!find) {
                System.out.println("Goldbach's conjecture is wrong.");
            }
        }
    }

    private static void genPrimes() {
        p = new boolean[N + 1];
        primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (!p[i]) {
                primes.add(i);
                for (int j = i + i; j <= N; j += i) {
                    p[j] = true;
                }
            }
        }
    }
}

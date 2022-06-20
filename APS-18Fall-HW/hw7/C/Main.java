import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static boolean check(int[] coins, int sum) {
        int n = coins.length;
        int[] dp = new int[sum + 1];
        int coin_sum = 0;
        for (int i = 0; i < n; i++) {
            coin_sum += coins[i];
        }
        if (coins[0] != 1 || !(coin_sum <= sum && sum <= 1e6)) {
            throw new RuntimeException();
        }
        dp[0] = 0;
        for (int i = 1; i <= sum; i++) {
            dp[i] = 0x3f3f3f3f;
            for (int j = 0; j < n; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        int best_greedy = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int tmp = sum;
            int cnt = 0;
            for (int j = i; j >= 0; j--) {
                int take = tmp / coins[j];
                cnt += take;
                tmp -= take * coins[j];
            }
            best_greedy = Math.min(best_greedy, cnt);
        }
        if (best_greedy > dp[sum]) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        if (n <= 2) {
            System.out.println("impossible");
        } else if (n == 3) {
            System.out.println("18\n1 5 8\n");
        } else {
            // (no y-1) y y+1 x
            // sum = x * 90 + y + y
            int x = 10000;
            int y = 105;
            int sum = x * 90 + y + y;
            int[] coins = new int[n];
            for (int i = 0; i < n - 3; i++) {
                coins[i] = i + 1;
            }
            coins[n - 3] = y;
            coins[n - 2] = y + 1;
            coins[n - 1] = x;
            if (!check(coins, sum)) {
                throw new RuntimeException();
            }
            System.out.println(sum);
            for (int i = 0; i < n; i++) {
                System.out.printf("%d%c", coins[i], (i < n - 1 ? ' ' : '\n'));
            }
        }
    }
}

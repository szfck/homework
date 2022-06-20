import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cas = Integer.parseInt(br.readLine());
        while (cas-- > 0) {
            int m = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[20002];
            Arrays.fill(dp, -1);
            dp[0] = 0;
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(br.readLine());
                for (int j = 20001 - x; j >= 0; j--) {
                    if (dp[j] > -1) {
                        if (dp[j + x] == -1 || dp[j + x] > dp[j] + 1) {
                            dp[j + x] = dp[j] + 1;
                        }
                    }
                }
            }
            for (int i = m; i <= 20001; i++) {
                if (dp[i] > -1) {
                    System.out.println(i + " " + dp[i]);
                    break;
                }
            }
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dp = new int[13];
        int[] fac = new int[13];
        fac[0] = 1;
        for (int i = 1; i <= 12; i++) {
            fac[i] = fac[i - 1] * i;
        }
        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= 12; i++) {
            dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]);
        }
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.printf("%d/%d%n", dp[n], fac[n]);
        }
    }

}

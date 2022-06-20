import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int n = in.nextInt();
            if (n == 0) break;
            int a = 0, b = 0;
            for (int i = 0, cur = 0; i < 32; i++) {
                if (((n >> i) & 1) == 0) continue;
                if (cur == 0) {
                    a += 1 << i;
                } else {
                    b += 1 << i;
                }
                cur ^= 1;
            }
            System.out.println(a + " " + b);
        }
    }
}
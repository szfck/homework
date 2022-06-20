import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (true) {
            int n = in.nextInt(), m = in.nextInt();
            if (n == 0 && m == 0) break;
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(in.nextInt());
            }
            int res = 0; 
            for (int i = 0; i < m; i++) {
                if (set.contains(in.nextInt())) {
                    res++;
                }
            }
            System.out.println(res);
        }
    }
}
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // for (int i = 0; i <= 2018; i++) {
        //     int j = (int) Math.sqrt(2018 * 2018 - i * i);
        //     if (j * j == 2018 * 2018 - i * i) {
        //         System.out.println(i + " " + j);
        //     }
        // }
        int[] dx = new int[] {0, 1118, 1118, 1680, 1680, 2018};
        int[] dy = new int[] {2018, 1680, -1680, 1118, -1118, 0};
        Set<String> set = new HashSet<>();
        int n = in.nextInt();
        long[] x = new long[n];
        long[] y = new long[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextLong();
            y[i] = in.nextLong();
            set.add(x[i] + " " + y[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < dx.length; j++) {
                long nx = x[i] + dx[j];
                long ny = y[i] + dy[j];
                if (set.contains(nx + " " + ny)) {
                    res++;
                }
            }
        }
        System.out.println(res);
    }
}
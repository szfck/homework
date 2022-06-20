import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Main {
    public static List<int[]> list;
    public static void dfs(int col, int[] row, int[] k1, int[] k2, int[] res) {
        if (col == 8) {
            list.add(Arrays.copyOf(res, 8));
            return;
        }
        for (int r = 0; r < 8; r++) {
            if (row[r] == 0 && k1[r - col + 7] == 0 && k2[r + col] == 0) {
                row[r] = 1;
                k1[r - col + 7] = 1;
                k2[r + col] = 1;
                res[col] = r;
                dfs(col + 1, row, k1, k2, res);
                row[r] = 0;
                k1[r - col + 7] = 0;
                k2[r + col] = 0;
            }
        }
    }
    public static void findAll() {
        int[] row = new int[8];
        // k1 = y - x + 7 [0, 14]
        int[] k1 = new int[15];
        // k2 = x + y [0, 14]
        int[] k2 = new int[15];
        dfs(0, row, k1, k2, new int[8]);

    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        list = new ArrayList<>();
        findAll();
        int cas = 1;
        while (in.hasNext()) {
            int[] row = new int[8];
            for (int i = 0; i < 8; i++) {
                row[i] = in.nextInt() - 1;
            }
            int min = 8;
            for (int[] vec : list) {
                int count = 0;
                for (int i = 0; i < 8; i++) {
                    if (row[i] != vec[i]) {
                        count++;
                    }
                }
                if (count < min) {
                    min = count;
                }
            }
            System.out.printf("Case %d: %d\n", cas++, min);
        }
    }
}
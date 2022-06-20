import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            char[][] strs = new char[n][3];
            for (int i = 0; i < n; i++) {
                String s = in.next();
                for (int j = 0; j < 3; j++) {
                    strs[i][j] = s.charAt(j);
                }
            }
            int r1 = -1;
            for (int i = 0; i < n; i++) {
                boolean flag = true;
                for (int j = 1; j < n; j++) {
                    if (strs[(j + i) % n][0] < strs[(j - 1 + i) % n][0]) {
                        flag = false;
                        break;
                    } 
                }
                if (flag) {
                    r1 = i;
                    break;
                }
            }
            if (r1 == -1) {
                System.out.println("impossible");
                continue;
            }
            int r2 = -1, r3 = -1;
            boolean find = false;
            for (int j = 0; j < n && !find; j++) {
                for (int k = 0; k < n && !find; k++) {
                    boolean tmp = true;
                    for (int i = 1; i < n; i++) {
                        String s1 = "" + strs[(i + r1) % n][0] + strs[(i + j) % n][1] + strs[(i + k) % n][2];
                        String s2 = "" + strs[(i + r1 + n - 1) % n][0] + strs[(i + j + n - 1) % n][1] + strs[(i + k + n - 1) % n][2];
                        if (s2.compareTo(s1) > 0) {
                            tmp = false;
                            break;
                        }
                    }
                    if (tmp) {
                        r2 = j;
                        r3 = k;
                        find = true;
                    }
                }
            }
            if (find) {
                System.out.println(r1 + " " + r2 + " " + r3);
            } else {
                System.out.println("impossible");
            } 
        }       
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static final int LEF = -1;
    public static final int RIG = -2;
    public static final long MOD = 1000000007;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.next();
            int n = s.length();
            int[] stk = new int[n + 1];
            int st = 1, en = 0;
            boolean flag = true;
            for (int i = 0; i < n && flag; i++) {
                char ch = s.charAt(i);
                if (ch == '(') {
                    stk[++en] = LEF;
                } else {
                    if (st > en) {
                        flag = false;
                        break;
                    }
                    if (stk[en] == LEF) {
                        stk[en] = 1;
                    } else {
                        long sum = 0;
                        while (st <= en && stk[en] != LEF) {
                            sum += stk[en--];
                            sum %= MOD;
                        }
                        if (st <= en && stk[en] == LEF) {
                            stk[en] = (int)(sum * sum % MOD);
                        } else {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            long res = 0;
            for (int i = st; i <= en; i++) {
                if (stk[i] < 0) {
                    flag = false;
                    break;
                }
                res += stk[i];
                res %= MOD;
            }
            if (flag) {
                System.out.println(res);
            } else {
                System.out.println(0);
            }
        }
    }
}
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Task task = new Task();
        while (in.hasNext()) {
            task.solve(in, out);
            out.flush();
        }
        out.close();
    }
    static class Task {
        static final String I = "illegal";
        static final String F = "first";
        static final String S = "second";
        static final String FW = "the first player won";
        static final String SW = "the second player won";
        static final String D = "draw";

        public void solve(Scanner in, PrintWriter out) {
            char[][] s = new char[3][3];
            for (int i = 0; i < 3; i++) {
                String str = in.next();
                for (int j = 0; j < 3; j++) {
                    s[i][j] = str.charAt(j);
                }
            }
            int X = 0, O = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (s[i][j] == 'X') X++;
                    else if (s[i][j] == '0') O++;
                }
            }
            char last;
            if (X == O) {
                last = '0';
            } else if (X == O + 1) {
                last = 'X';
            } else {
                out.println(I);
                return;
            }
            if (!win(s)) {
                if (O + X == 9) {
                    out.println(D);
                } else {
                    out.println(last == 'X' ? S : F);
                }
                return;
            }
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (s[i][j] == last) {
                        s[i][j] = '.';
                        if (!win(s)) {
                            out.println(last == 'X' ? FW : SW);
                            return;
                        }
                        s[i][j] = last;
                    }
                }
            }
            
            out.println(I);
        }

        boolean win(char[][] s) {
            boolean flag = false;
            for (int i = 0; i < 3; i++) {
                flag |= check(s[i][0], s[i][1], s[i][2]);
                flag |= check(s[0][i], s[1][i], s[2][i]);
            }
            flag |= check(s[0][0], s[1][1], s[2][2]);
            flag |= check(s[0][2], s[1][1], s[2][0]);
            return flag;
        }

        boolean check(char a, char b, char c) {
            return a != '.' && a == b && b == c;
        }
    }
}

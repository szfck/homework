import java.util.*;

import java.io.*;
public class Main {
    public static Map<Integer, char[]> mp;
    public static int getPri(char ch) {
        if (ch == '+' || ch == '-') return 1;
        return 2;
    }
    public static int calc(int x, char op, int y) {
        if (op == '+') return x + y;
        if (op == '-') return x - y;
        if (op == '*') return x * y;
        if (op == '/') return x / y;
        return 0;
    }
    public static char[] operators = new char[] {'+', '-', '*', '/'};
    public static void dfs(int id, char[] res) {
        if (id == 3) {
            Stack<Character> op = new Stack<>();
            Stack<Integer> num = new Stack<>();
            num.push(4);
            for (int i = 0; i < 3; i++) {
                if (op.empty() || getPri(res[i]) > getPri(op.peek())) {
                    op.push(res[i]);
                } else {
                    int v2 = num.pop();
                    int v1 = num.pop();
                    char symbol = op.pop();
                    num.push(calc(v1, symbol, v2));
                    op.push(res[i]);
                }
                num.push(4);
            }
            while (!op.empty()) {
                int v2 = num.pop();
                int v1 = num.pop();
                char symbol = op.pop();
                num.push(calc(v1, symbol, v2));
            }
            int ans = num.peek();
            if (!mp.containsKey(ans)) {
                mp.put(ans, Arrays.copyOf(res, 3));
            }
            return;
        }
        for (char ch : operators) {
            res[id] = ch;
            dfs(id + 1, res);
        }
    }
    public static void findAll() {
        dfs(0, new char[3]);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        mp = new HashMap<>();
        findAll();
        int n = in.nextInt();
        while (n-- > 0) {
            int sum = in.nextInt();
            if (!mp.containsKey(sum)) {
                System.out.println("no solution");
            } else {
                char[] res = mp.get(sum);
                System.out.printf("4 %c 4 %c 4 %c 4 = %d\n", res[0], res[1], res[2], sum);
            }
        }

    }
}
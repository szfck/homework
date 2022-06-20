import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            Set<String> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String s = in.next();
                set.add(s);
            }
            System.out.println(set.size());
        }
    }
}
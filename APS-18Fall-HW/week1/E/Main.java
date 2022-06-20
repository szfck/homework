import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while (n-- > 0) {
            String s = in.next();
            for (int i = 0; i < s.length(); i++) {
                int j = i;
                while (j + 1 < s.length() && s.charAt(j + 1) == s.charAt(j)) {
                    j++;
                }
                int len = j - i + 1;
                if (len > 1) {
                    System.out.print(len);
                }
                System.out.print(s.charAt(i));
                i = j;
            }
            System.out.println();
        }
    }
}

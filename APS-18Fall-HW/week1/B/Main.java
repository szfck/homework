import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += in.nextInt();
            }
            System.out.println(sum);
        }
    }
}

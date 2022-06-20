import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += in.nextInt();
            }
            System.out.println(sum);
        }
    }
}

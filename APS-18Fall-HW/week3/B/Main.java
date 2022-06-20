import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();
        int[] cnt = new int[26];
        for (int i = 0; i < str.length(); i++) {
            cnt[str.charAt(i) - 'a']++;
        }
        Arrays.sort(cnt);
        int res = 0;
        for (int i = 0; i < 24; i++) {
            res += cnt[i];
        }
        System.out.println(res);
    }
}
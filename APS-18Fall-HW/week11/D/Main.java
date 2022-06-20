import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (".".equals(line)) break;
            int len = line.length();
            int[] next = new int[len + 1];
            next[0] = next[1] = 0;
            int j = 0;
            for (int i = 2; i <= len; i++) {
                while (j > 0 && line.charAt(j) != line.charAt(i - 1)) {
                    j = next[j];
                }
                if (line.charAt(j) == line.charAt(i - 1)) j++;
                next[i] = j;
            }
            System.out.println(len / (len - next[len]));
        }

    }


}
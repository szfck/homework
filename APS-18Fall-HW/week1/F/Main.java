import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        int c;
        try {
            while ((c = br.read()) != -1) {
                char ch = (char) c;
                if (ch == '"') {
                    if (cnt == 0) {
                        sb.append("``");
                    } else {
                        sb.append("''");
                    }
                    cnt = 1 - cnt;
                } else {
                    sb.append(ch);
                }
            }
        } catch (Exception e) { }
        System.out.print(sb.toString());
    }
}

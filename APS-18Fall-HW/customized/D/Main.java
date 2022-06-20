import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n + 1];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        long[] sum = new long[n + 1];
        long[] min = new long[n + 1];
        int[] minIndex = new int[n + 1];
        minIndex[n] = n;
        for (int i = n - 1; i >= 0; i--) {
            sum[i] = sum[i + 1] + a[i];
            min[i] = min[i + 1];
            minIndex[i] = minIndex[i + 1];
            if (sum[i] <= min[i]) {
                min[i] = sum[i];
                minIndex[i] = i;
            }
        }
        long res = Long.MIN_VALUE;
        int start = -1, end = -1;
        for (int i = 0; i < n; i++) {
            long cur = sum[i] - min[i + 1];
            int s = i, e = minIndex[i + 1] - 1;
            if (cur > res) {
                res = cur;
                start = s; end = e;
            }
        }
        System.out.println(res);
        System.out.println(start + " " + end);
    }

}
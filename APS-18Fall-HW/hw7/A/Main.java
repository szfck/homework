import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        Long l, r;
        Node(Long l, Long r) {
            this.l = l;
            this.r = r;
        }
    }
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        int n = in.nextInt();
        long k = in.nextLong();
        Node[] seg = new Node[n];
        for (int i = 0; i < n; i++) {
            long l = in.nextLong(), r = l + in.nextLong() - 1;
            seg[i] = new Node(l, r);
        }
        Arrays.sort(seg, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.l.compareTo(o2.l);
            }
        });
        Node[] merge = new Node[n];
        int ptr = 0;
        merge[ptr++] = seg[0];
        for (int i = 1; i < n; i++) {
            if (seg[i].l > merge[ptr - 1].r) {
                merge[ptr++] = seg[i];
            } else {
                merge[ptr - 1].r = Math.max(merge[ptr - 1].r, seg[i].r);
            }
        }
        long res = 0;
        long st = -1;
        for (int i = 0; i < ptr; i++) {
            long l = merge[i].l, r = merge[i].r;
            if (st > r) continue;
            if (st < l) st = l;
            long step = (r - st + 1 + k - 1) / k;
            res += step;
            st += step * k;
        }
        System.out.println(res);
    }
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
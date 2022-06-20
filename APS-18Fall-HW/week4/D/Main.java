import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Main {
    static class TrieNode {
        public TrieNode[] nxt;
        public boolean end;
        public int count;
        TrieNode() {
            this.nxt = new TrieNode[10];
            end = false;
            count = 0;
        }
    }
    public static boolean insert(TrieNode ptr, String s) {
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            if (ptr.nxt[num] == null) {
                ptr.nxt[num] = new TrieNode();
            }
            ptr = ptr.nxt[num];
            if (ptr.end) return false;
            ptr.count++;
        }
        ptr.end = true;
        return ptr.count == 1;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        while (T-- > 0) {
            int n = in.nextInt();
            TrieNode root = new TrieNode();
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                flag &= insert(root, in.next());
            }
            System.out.println(flag ? "YES" : "NO");
        }
    }
}
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] notes = new String[] {
           "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"
        };
        int[] steps = new int[] {
            2, 2, 1, 2, 2, 2, 1
        };
        int n = in.nextInt();
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = in.next();
        }
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < notes.length; i++) {
            Set<String> set = new HashSet<>();
            int pos = i;
            for (int j = 0; j < steps.length; j++) {
                pos += steps[j];
                pos %= notes.length;
                set.add(notes[pos]);
            }
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if (!set.contains(strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(notes[i]);
            }
        }
        Collections.sort(res);
        if (res.isEmpty()) {
            System.out.println("none");
        } else {
            for (String str : res) {
                System.out.printf("%s ", str);
            }
            System.out.println();
        }
    }
}
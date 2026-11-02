package textanalysis.suffix;

import java.util.Arrays;

public final class SuffixArray {

    private SuffixArray() {
    }

    public static int[] build(String s) {
        int n = s.length();
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (x, y) -> compareSuffixes(s, x, y));
        int[] sa = new int[n];
        for (int i = 0; i < n; i++) {
            sa[i] = idx[i];
        }
        return sa;
    }

    private static int compareSuffixes(String s, int x, int y) {
        int n = s.length();
        while (x < n && y < n) {
            char a = s.charAt(x);
            char b = s.charAt(y);
            if (a != b) {
                return a - b;
            }
            x++;
            y++;
        }
        return (n - x) - (n - y);
    }
}

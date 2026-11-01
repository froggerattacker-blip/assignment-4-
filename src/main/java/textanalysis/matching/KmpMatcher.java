package textanalysis.matching;

import java.util.ArrayList;
import java.util.List;

public class KmpMatcher implements Matcher {

    private long comparisons;

    @Override
    public String name() {
        return "KMP";
    }

    public int[] prefixTable(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;
        while (i < m) {
            comparisons++;
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else if (len > 0) {
                len = lps[len - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }

    @Override
    public List<Integer> search(String text, String pattern) {
        comparisons = 0;
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if (m == 0 || m > n) {
            return result;
        }
        int[] lps = prefixTable(pattern);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (true) {
                comparisons++;
                if (text.charAt(i) == pattern.charAt(j)) {
                    j++;
                    break;
                }
                if (j == 0) {
                    break;
                }
                j = lps[j - 1];
            }
            if (j == m) {
                result.add(i - m + 1);
                j = lps[j - 1];
            }
        }
        return result;
    }

    @Override
    public long comparisons() {
        return comparisons;
    }
}

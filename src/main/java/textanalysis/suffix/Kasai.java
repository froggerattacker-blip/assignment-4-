package textanalysis.suffix;

public class Kasai {

    private long comparisons;

    public int[] build(String s, int[] sa) {
        comparisons = 0;
        int n = s.length();
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }
        int[] lcp = new int[n];
        int h = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] == 0) {
                h = 0;
                continue;
            }
            int j = sa[rank[i] - 1];
            while (i + h < n && j + h < n) {
                comparisons++;
                if (s.charAt(i + h) != s.charAt(j + h)) {
                    break;
                }
                h++;
            }
            lcp[rank[i]] = h;
            if (h > 0) {
                h--;
            }
        }
        return lcp;
    }

    public long comparisons() {
        return comparisons;
    }
}

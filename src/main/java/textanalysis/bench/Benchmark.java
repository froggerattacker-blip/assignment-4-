package textanalysis.bench;

import java.util.List;

public class Benchmark {

    public static void main(String[] args) {
        List<String[]> dp = new DpExperiment().run();
        List<String[]> matching = new MatchingExperiment().run();
        List<String[]> kasai = new KasaiExperiment().run();

        print("Dynamic programming", new String[]{"algorithm", "n", "avg_ms", "cells", "result"}, dp);
        print("String matching", new String[]{"algorithm", "case", "n", "m", "avg_ms", "comparisons", "matches"}, matching);
        print("Kasai LCP", new String[]{"n", "avg_ms", "comparisons", "max_lcp"}, kasai);
    }

    private static void print(String title, String[] header, List<String[]> rows) {
        System.out.println();
        System.out.println(title);
        System.out.println(String.join(" | ", header));
        for (String[] row : rows) {
            System.out.println(String.join(" | ", row));
        }
    }
}

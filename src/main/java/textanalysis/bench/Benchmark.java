package textanalysis.bench;

import java.util.List;

public class Benchmark {

    public static void main(String[] args) {
        List<String[]> dp = new DpExperiment().run();

        print("Dynamic programming", new String[]{"algorithm", "n", "avg_ms", "cells", "result"}, dp);
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

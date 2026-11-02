package textanalysis.bench;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Benchmark {

    public static void main(String[] args) throws IOException {
        Path out = Path.of(args.length > 0 ? args[0] : "results");
        Files.createDirectories(out);

        List<String[]> dp = new DpExperiment().run();
        List<String[]> matching = new MatchingExperiment().run();
        List<String[]> kasai = new KasaiExperiment().run();

        String[] dpHeader = {"algorithm", "n", "avg_ms", "cells", "result"};
        String[] matchingHeader = {"algorithm", "case", "n", "m", "avg_ms", "comparisons", "matches"};
        String[] kasaiHeader = {"n", "avg_ms", "comparisons", "max_lcp"};

        print("Dynamic programming", dpHeader, dp);
        print("String matching", matchingHeader, matching);
        print("Kasai LCP", kasaiHeader, kasai);

        write(out.resolve("dp.csv"), dpHeader, dp);
        write(out.resolve("matching.csv"), matchingHeader, matching);
        write(out.resolve("kasai.csv"), kasaiHeader, kasai);
    }

    private static void print(String title, String[] header, List<String[]> rows) {
        System.out.println();
        System.out.println(title);
        System.out.println(String.join(" | ", header));
        for (String[] row : rows) {
            System.out.println(String.join(" | ", row));
        }
    }

    private static void write(Path file, String[] header, List<String[]> rows) throws IOException {
        try (PrintWriter w = new PrintWriter(Files.newBufferedWriter(file))) {
            w.println(String.join(",", header));
            for (String[] row : rows) {
                w.println(String.join(",", row));
            }
        }
    }
}

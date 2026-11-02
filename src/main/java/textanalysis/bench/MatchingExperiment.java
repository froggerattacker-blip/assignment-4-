package textanalysis.bench;

import java.util.ArrayList;
import java.util.List;
import textanalysis.matching.KmpMatcher;
import textanalysis.matching.Matcher;
import textanalysis.matching.NaiveMatcher;
import textanalysis.matching.RabinKarpMatcher;

public class MatchingExperiment {

    public static final int[] SIZES = {10_000, 50_000, 100_000};
    public static final int PATTERN_LENGTH = 20;

    private final Matcher[] matchers = {new NaiveMatcher(), new KmpMatcher(), new RabinKarpMatcher()};

    public List<String[]> run() {
        List<String[]> rows = new ArrayList<>();
        InputGenerator gen = new InputGenerator(InputGenerator.SEED);
        for (int n : SIZES) {
            String text = gen.randomString(n);
            String present = text.substring(n / 2, n / 2 + PATTERN_LENGTH);
            String absent = absentPattern(gen, text);
            measure(rows, "present", text, present);
            measure(rows, "absent", text, absent);

            String repetitive = "a".repeat(n);
            String hard = "a".repeat(PATTERN_LENGTH - 1) + "b";
            measure(rows, "repetitive", repetitive, hard);
        }
        return rows;
    }

    private String absentPattern(InputGenerator gen, String text) {
        NaiveMatcher check = new NaiveMatcher();
        String p = gen.randomString(PATTERN_LENGTH);
        while (!check.search(text, p).isEmpty()) {
            p = gen.randomString(PATTERN_LENGTH);
        }
        return p;
    }

    private void measure(List<String[]> rows, String kind, String text, String pattern) {
        List<Integer> expected = null;
        for (Matcher matcher : matchers) {
            double ms = Timer.averageMs(() -> matcher.search(text, pattern));
            List<Integer> found = matcher.search(text, pattern);
            if (expected == null) {
                expected = found;
            } else if (!expected.equals(found)) {
                throw new IllegalStateException(matcher.name() + " disagrees on " + kind + " n=" + text.length());
            }
            rows.add(new String[]{
                    matcher.name(), kind, String.valueOf(text.length()), String.valueOf(pattern.length()),
                    DpExperiment.fmt(ms), String.valueOf(matcher.comparisons()), String.valueOf(found.size())
            });
        }
    }
}

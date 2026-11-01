package textanalysis;

import java.util.List;
import textanalysis.matching.KmpMatcher;
import textanalysis.matching.Matcher;
import textanalysis.matching.NaiveMatcher;
import textanalysis.matching.RabinKarpMatcher;

public class MatchingTest {

    private static final Matcher[] MATCHERS = {new NaiveMatcher(), new KmpMatcher(), new RabinKarpMatcher()};

    public static void run(Check c) {
        same(c, "beginning", "abcdef", "abc", List.of(0));
        same(c, "middle", "xxabcxx", "abc", List.of(2));
        same(c, "end", "xxxabc", "abc", List.of(3));
        same(c, "multiple", "abababab", "aba", List.of(0, 2, 4));
        same(c, "multiple aaaa", "aaaaa", "aa", List.of(0, 1, 2, 3));
        same(c, "absent", "abcdef", "xyz", List.of());
        same(c, "longer than text", "abc", "abcd", List.of());
        same(c, "empty pattern", "abc", "", List.of());
        same(c, "empty text", "", "a", List.of());
        same(c, "whole text", "abc", "abc", List.of(0));

    }

    private static void same(Check c, String name, String text, String pattern, List<Integer> expected) {
        for (Matcher m : MATCHERS) {
            c.equal(m.name() + " " + name, expected, m.search(text, pattern));
        }
    }
}

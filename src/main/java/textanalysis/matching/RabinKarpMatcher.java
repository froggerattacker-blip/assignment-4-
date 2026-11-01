package textanalysis.matching;

import java.util.ArrayList;
import java.util.List;

public class RabinKarpMatcher implements Matcher {

    private static final long BASE = 256;

    private final long mod;
    private long comparisons;
    private long collisions;

    public RabinKarpMatcher() {
        this(1_000_000_007L);
    }

    public RabinKarpMatcher(long mod) {
        this.mod = mod;
    }

    @Override
    public String name() {
        return "Rabin-Karp";
    }

    @Override
    public List<Integer> search(String text, String pattern) {
        comparisons = 0;
        collisions = 0;
        List<Integer> result = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if (m == 0 || m > n) {
            return result;
        }
        long high = 1;
        for (int i = 0; i < m - 1; i++) {
            high = high * BASE % mod;
        }
        long patternHash = 0;
        long windowHash = 0;
        for (int i = 0; i < m; i++) {
            patternHash = (patternHash * BASE + pattern.charAt(i)) % mod;
            windowHash = (windowHash * BASE + text.charAt(i)) % mod;
        }
        for (int i = 0; i <= n - m; i++) {
            if (patternHash == windowHash) {
                if (matchesAt(text, pattern, i)) {
                    result.add(i);
                } else {
                    collisions++;
                }
            }
            if (i < n - m) {
                windowHash = (windowHash - text.charAt(i) * high % mod + mod) % mod;
                windowHash = (windowHash * BASE + text.charAt(i + m)) % mod;
            }
        }
        return result;
    }

    private boolean matchesAt(String text, String pattern, int start) {
        for (int j = 0; j < pattern.length(); j++) {
            comparisons++;
            if (text.charAt(start + j) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public long comparisons() {
        return comparisons;
    }

    public long collisions() {
        return collisions;
    }
}

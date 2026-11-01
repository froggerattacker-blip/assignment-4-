package textanalysis.matching;

import java.util.List;

public interface Matcher {

    String name();

    List<Integer> search(String text, String pattern);

    long comparisons();
}

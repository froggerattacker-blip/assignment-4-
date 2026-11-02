package textanalysis;

import textanalysis.suffix.Kasai;
import textanalysis.suffix.SuffixArray;

public class KasaiTest {

    public static void run(Check c) {
        int[] sa = {5, 3, 1, 0, 4, 2};
        c.equal("suffix array banana", sa, SuffixArray.build("banana"));
        c.equal("kasai banana", new int[]{0, 1, 3, 0, 0, 2}, new Kasai().build("banana", sa));
    }
}

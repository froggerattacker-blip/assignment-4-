package textanalysis;

import textanalysis.suffix.Kasai;
import textanalysis.suffix.SuffixArray;

public class KasaiTest {

    public static void run(Check c) {
        int[] sa = {5, 3, 1, 0, 4, 2};
        c.equal("suffix array banana", sa, SuffixArray.build("banana"));
        c.equal("kasai banana", new int[]{0, 1, 3, 0, 0, 2}, new Kasai().build("banana", sa));

        int[] saMiss = {10, 7, 4, 1, 0, 9, 8, 6, 3, 5, 2};
        c.equal("suffix array mississippi", saMiss, SuffixArray.build("mississippi"));
        c.equal("kasai mississippi", new int[]{0, 1, 1, 4, 0, 0, 1, 0, 2, 1, 3}, new Kasai().build("mississippi", saMiss));

        c.equal("kasai single char", new int[]{0}, new Kasai().build("a", new int[]{0}));
        c.equal("kasai aaaa", new int[]{0, 1, 2, 3}, new Kasai().build("aaaa", SuffixArray.build("aaaa")));
    }
}

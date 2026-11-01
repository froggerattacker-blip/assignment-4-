package textanalysis;

import textanalysis.dp.Lcs;

public class LcsTest {

    public static void run(Check c) {
        c.equal("lcs identical", 6, Lcs.length("banana", "banana"));
        c.equal("lcs identical sequence", "banana", Lcs.sequence("banana", "banana"));
        c.equal("lcs different", 0, Lcs.length("abc", "xyz"));
        c.equal("lcs empty first", 0, Lcs.length("", "abc"));
        c.equal("lcs empty second", 0, Lcs.length("abc", ""));
        c.equal("lcs both empty", 0, Lcs.length("", ""));
        c.equal("lcs classic", 4, Lcs.length("ABCBDAB", "BDCABA"));
        c.equal("lcs classic sequence length", 4, Lcs.sequence("ABCBDAB", "BDCABA").length());
        c.equal("lcs AGGTAB", "GTAB", Lcs.sequence("AGGTAB", "GXTXAYB"));
    }
}

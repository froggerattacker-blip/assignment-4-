package textanalysis.bench;

import java.util.ArrayList;
import java.util.List;
import textanalysis.suffix.Kasai;
import textanalysis.suffix.SuffixArray;

public class KasaiExperiment {

    public static final int[] SIZES = {1_000, 5_000, 10_000};

    public List<String[]> run() {
        List<String[]> rows = new ArrayList<>();
        InputGenerator gen = new InputGenerator(InputGenerator.SEED);
        Kasai kasai = new Kasai();
        for (int n : SIZES) {
            String s = gen.randomString(n);
            int[] sa = SuffixArray.build(s);
            double ms = Timer.averageMs(() -> kasai.build(s, sa));
            int[] lcp = kasai.build(s, sa);
            int max = 0;
            for (int v : lcp) {
                max = Math.max(max, v);
            }
            rows.add(new String[]{String.valueOf(n), DpExperiment.fmt(ms), String.valueOf(kasai.comparisons()), String.valueOf(max)});
        }
        return rows;
    }
}

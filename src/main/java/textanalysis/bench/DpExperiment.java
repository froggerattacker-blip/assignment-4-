package textanalysis.bench;

import java.util.ArrayList;
import java.util.List;
import textanalysis.dp.EditDistance;
import textanalysis.dp.Lcs;

public class DpExperiment {

    public static final int[] SIZES = {100, 500, 1000};

    public List<String[]> run() {
        List<String[]> rows = new ArrayList<>();
        InputGenerator gen = new InputGenerator(InputGenerator.SEED);
        for (int n : SIZES) {
            String a = gen.randomString(n);
            String b = gen.randomString(n);
            long cells = (long) n * n;

            double lcsMs = Timer.averageMs(() -> Lcs.length(a, b));
            int lcs = Lcs.length(a, b);
            rows.add(new String[]{"LCS", String.valueOf(n), fmt(lcsMs), String.valueOf(cells), String.valueOf(lcs)});

            double edMs = Timer.averageMs(() -> EditDistance.distance(a, b));
            int ed = EditDistance.distance(a, b);
            rows.add(new String[]{"EditDistance", String.valueOf(n), fmt(edMs), String.valueOf(cells), String.valueOf(ed)});
        }
        return rows;
    }

    static String fmt(double ms) {
        return String.format(java.util.Locale.US, "%.4f", ms);
    }
}

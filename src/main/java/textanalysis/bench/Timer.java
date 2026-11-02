package textanalysis.bench;

import java.util.function.Supplier;

public final class Timer {

    public static final int RUNS = 5;

    private static volatile Object sink;

    private Timer() {
    }

    public static double averageMs(Supplier<?> task) {
        sink = task.get();
        long total = 0;
        for (int r = 0; r < RUNS; r++) {
            long start = System.nanoTime();
            Object result = task.get();
            total += System.nanoTime() - start;
            sink = result;
        }
        return total / (double) RUNS / 1_000_000.0;
    }
}

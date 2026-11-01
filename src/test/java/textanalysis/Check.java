package textanalysis;

import java.util.Objects;

public class Check {

    private int passed;
    private int failed;

    public void equal(String name, Object expected, Object actual) {
        if (Objects.deepEquals(expected, actual)) {
            passed++;
        } else {
            failed++;
            System.out.println("FAIL " + name + ": expected " + str(expected) + ", got " + str(actual));
        }
    }

    public void isTrue(String name, boolean value) {
        equal(name, true, value);
    }

    private static String str(Object o) {
        if (o instanceof int[] a) {
            return java.util.Arrays.toString(a);
        }
        return String.valueOf(o);
    }

    public int passed() {
        return passed;
    }

    public int failed() {
        return failed;
    }
}

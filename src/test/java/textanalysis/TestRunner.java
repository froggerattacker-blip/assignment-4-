package textanalysis;

public class TestRunner {

    public static void main(String[] args) {
        Check c = new Check();
        LcsTest.run(c);
        System.out.println("passed: " + c.passed() + ", failed: " + c.failed());
        if (c.failed() > 0) {
            System.exit(1);
        }
    }
}

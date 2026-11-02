package textanalysis.bench;

import java.util.Random;

public class InputGenerator {

    public static final String ALPHABET = "abcd";
    public static final long SEED = 42L;

    private final Random random;

    public InputGenerator(long seed) {
        this.random = new Random(seed);
    }

    public String randomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}

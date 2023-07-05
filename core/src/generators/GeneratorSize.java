package generators;

import java.util.concurrent.ThreadLocalRandom;

public class GeneratorSize {

    private static final int MAX_SIZE = 30;
    private static final int MIN_SIZE = 10;

    public static int generate() {
        return ThreadLocalRandom.current().nextInt(MIN_SIZE, MAX_SIZE + 1);
    }

}

package generators;

import com.badlogic.gdx.graphics.Texture;
import data.Colors;

import java.util.concurrent.ThreadLocalRandom;

public class GeneratorSkin {

    private static final int MIN = 1;
    private static final int MAX = Colors.getMax();

    public static Colors generate() {
        return Colors.searchByCode(ThreadLocalRandom.current().nextInt(MIN, MAX + 1));
    }
}

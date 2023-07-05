package generators;

import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.ThreadLocalRandom;

import static configurations.ScreenConfiguration.*;

public class GeneratorPosition {

    private static int radius = 50;

    public static Vector2 generate() {
        return new Vector2(ThreadLocalRandom.current().nextInt(WIDTH_LEFT, WIDTH_RIGHT + 1),
                ThreadLocalRandom.current().nextInt(HEIGHT_DOWN, HEIGHT_UP + 1));
    }

    //Когда добавлю создание хвоста, тогда сделаю генератор положения, на котором никого нет

}

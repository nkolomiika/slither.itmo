package managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import data.Food;
import data.Player;
import data.Snake;
import generators.GeneratorPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static configurations.ScreenConfiguration.HEIGHT_DOWN;
import static configurations.ScreenConfiguration.WIDTH_LEFT;

public class SnakeManager {

    public Player mainSnake;
    public List<Snake> bots;

    public SnakeManager(Player mainSnake) {
        this.mainSnake = mainSnake;
        this.bots = new ArrayList<>();
    }

    public void render(Batch batch) {
        mainSnake.render(batch);
        BitmapFont font = new BitmapFont(); //or use alex answer to use custom font
        font.getData().setScale(4f);
        font.draw(batch,
                Float.toString(mainSnake.point),
                mainSnake.position.x + WIDTH_LEFT - 150 - (int) Math.log10(mainSnake.point) * 15,
                mainSnake.position.y + HEIGHT_DOWN - 10
        );
        for (Snake bot : bots) {
            bot.render(batch);
        }
    }

    public void moveTo(FoodManager foodManager) {
        mainSnake.moveTo();
        for (Snake bot : bots) {
            Food tmp = foodManager.findNearestFood(bot);
            bot.speed = new Vector2(tmp.position)
                    .sub(bot.position)
                    .setLength(1f);
            bot.moveTo();
            //System.out.println(bot.position.toString());
        }
    }

    public void generateBots(int count){
        for (int i =0; i< count; i++){
            this.addBot();
        }
    }

    public void addBot() {
        bots.add(new Snake());
    }

    public void addBot(Snake snake) {
        bots.add(snake);
    }

    public void removeBot(Snake snake) {
        bots.remove(snake);
        //snake.death();
    }

    public void eat(EatManager eatManager) {
        eatManager.eat(mainSnake);
        for (Snake bot : bots) {
            eatManager.eat(bot);
        }
    }
}

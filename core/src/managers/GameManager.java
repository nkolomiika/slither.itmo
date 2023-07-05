package managers;

import configurations.ScreenConfiguration;
import data.Snake;

import java.util.ArrayList;
import java.util.List;

import static configurations.ScreenConfiguration.*;

public class GameManager {

    public FoodManager foodManager;
    public SnakeManager snakeManager;

    public GameManager(FoodManager foodManager, SnakeManager snakeManager) {
        this.snakeManager = snakeManager;
        this.foodManager = foodManager;
    }

    public void run() {
        List<Snake> deathBots = new ArrayList<>();
        for (Snake bot : snakeManager.bots) {
            if (!snakeManager.mainSnake.pvp(bot)) {
                snakeManager.mainSnake.death(foodManager);
            }
            if (!bot.pvp(snakeManager.mainSnake)) {
                bot.death(foodManager);
                deathBots.add(bot);
            }
        }
        for (int a = 0; a < snakeManager.bots.size(); a++) {
            for (int b = a + 1; b < snakeManager.bots.size(); b++) {
                Snake enemy = snakeManager.bots.get(a);
                Snake target = snakeManager.bots.get(b);
                if (!enemy.pvp(target)) {
                    enemy.death(foodManager);
                    deathBots.add(enemy);
                }
                if (!target.pvp(enemy)) {
                    target.death(foodManager);
                    deathBots.add(target);
                }
            }
        }
        if (snakeManager.mainSnake.position.x == WIDTH_LEFT ||
                snakeManager.mainSnake.position.x == WIDTH_RIGHT ||
                snakeManager.mainSnake.position.y == HEIGHT_UP ||
                snakeManager.mainSnake.position.y == HEIGHT_DOWN) {

            snakeManager.mainSnake.death(foodManager);
        }
        for (Snake bot : snakeManager.bots) {
            if (bot.position.x == WIDTH_LEFT ||
                    bot.position.x == WIDTH_RIGHT ||
                    bot.position.y == HEIGHT_UP ||
                    bot.position.y == HEIGHT_DOWN) {

                bot.death(foodManager);
                deathBots.add(bot);
            }
        }
        for (Snake bot : deathBots) {
            snakeManager.removeBot(bot);
            snakeManager.addBot();
        }
    }
}

package managers;

import data.Food;
import data.Snake;

public class EatManager {
    private final int RADIUS = 30;

    private FoodManager foodManager;

    public EatManager(FoodManager foodManager) {
        this.foodManager = foodManager;
    }

    public Food checkPosition(Snake snake) {
        for (Food food : foodManager.food) {
            if ((snake.position.x < food.position.x + 2 * RADIUS && snake.position.x > food.position.x - 2 * RADIUS)
                    && (snake.position.y < food.position.y + 2 * RADIUS && snake.position.y > food.position.y - 2 * RADIUS)) {
                food.moveTo(snake.position);
            }
            if ((snake.position.x < food.position.x + RADIUS && snake.position.x > food.position.x - RADIUS)
                    && (snake.position.y < food.position.y + RADIUS && snake.position.y > food.position.y - RADIUS)) {
                return food;
            }
        }
        return null;
    }

    public void checkPoints(Snake snake) {
        if ((int) (snake.point) / 200 > snake.tail.size() - 5) {
            snake.addTail();
        } else if ((int) (snake.point) / 200 < snake.tail.size() - 5)
            foodManager.generateFood(snake.destroyTail(), snake.skin);
    }

    public void eat(Snake snake) {
        Food rmvFood = this.checkPosition(snake);
        if (rmvFood != null) {
            snake.point = snake.point + rmvFood.point;
            foodManager.removeFood(rmvFood);
            rmvFood.dispose();
            foodManager.createFood();
        }
        this.checkPoints(snake);
    }

}

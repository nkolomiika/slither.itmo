package managers;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import data.Colors;
import data.Food;
import data.Snake;

import java.util.ArrayList;
import java.util.List;

public class FoodManager extends Thread {

    public List<Food> food = new ArrayList<>();
    private final int COUNT = 500;

    public void generateFood() {
        for (int i = 0; i < COUNT; i++) {
            food.add(new Food());
        }
    }

    public void generateFood(Vector2 position){
        food.add(new Food(position));
    }

    public void generateFood(Vector2 position, Colors color){
        food.add(new Food(position, color));
    }

    public void render(Batch batch) {
        for (Food f : food) {
            f.render(batch);
        }
    }

    public void removeFood(Food rmvFood) {
        food.remove(rmvFood);
    }

    public void createFood() {
        food.add(new Food());
    }

    public void dispose(){
        for (Food f : food){
            f.dispose();
        }
    }

    public Food findNearestFood(Snake snake){
        double min = Double.MAX_VALUE;
        Food res = null;
        for (Food f : food){
            if (snake.position.dst(f.position) < min){
                min = snake.position.dst(f.position);
                res = f;
            }
        }
        return res;
    }

}

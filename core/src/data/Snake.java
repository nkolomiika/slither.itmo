package data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import generators.GeneratorPosition;
import generators.GeneratorSkin;
import managers.FoodManager;

import java.util.LinkedList;
import java.util.List;

import static configurations.ScreenConfiguration.*;

public class Snake {

    public final float size = 64;
    public final float halfSize = size / 2;
    public TextureRegion textureRegion;
    public Texture texture;
    public long id;
    public Vector2 position;
    public float point;
    public List<SnakePoints> tail;
    public float scale = 1.0f;
    public Colors skin;
    public Vector2 speed;
    private int velocity = 3;
    private final int SPEED_LOW = 3;
    private final int SPEED_UP = 6;

    public Snake(Colors skin, Vector2 position) {
        this.skin = skin;
        this.position = position;
    }

    public Snake() {
        this.position = GeneratorPosition.generate();
        this.speed = new Vector2(1, 0);
        this.skin = GeneratorSkin.generate();
        this.texture = new Texture(".\\skins\\heads\\" + skin.toString().toLowerCase() + ".png");
        this.textureRegion = new TextureRegion(this.texture);
        this.point = 0;
        this.tail = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            this.addTail();
        }
    }

    public void moveTo() {
        Vector2 tmpSpeed = new Vector2(speed);
        position.add(tmpSpeed.scl(velocity));
        if (position.x < WIDTH_LEFT) {
            position.x = WIDTH_LEFT;
        }
        if (position.x > WIDTH_RIGHT) {
            position.x = WIDTH_RIGHT;
        }
        if (position.y > HEIGHT_UP) {
            position.y = HEIGHT_UP;
        }
        if (position.y < HEIGHT_DOWN) {
            position.y = HEIGHT_DOWN;
        }

        for (SnakePoints point : tail) {
            point.moveTo();
            point.setSpeed(speed, velocity);
        }
    }

    public void render(Batch batch) {
        batch.draw(
                textureRegion,
                position.x,
                position.y,
                halfSize,
                halfSize,
                size,
                size,
                scale,
                scale,
                speed.angleDeg()
        );
        for (SnakePoints point : tail) {
            point.render(batch);
        }
    }

    public void speedUp() {
        if (point < 1) {
            this.velocity = SPEED_LOW;
            return;
        }
        point -= 1;
        this.velocity = SPEED_UP;
    }

    public void speedDown() {
        this.velocity = SPEED_LOW;
    }

    public void addTail() {
        if (tail.size() == 0)
            tail.add(new SnakePoints(this.position, this.speed, this.skin));
        else
            tail.add(new SnakePoints(tail.get(tail.size() - 1).position, tail.get(tail.size() - 1).speed, this.skin));
    }

    public void death(FoodManager foodManager) {
        //System.out.println(1);
        while (tail.size() != 0) {
            foodManager.generateFood(destroyTail(), skin);
        }
        this.dispose();
        foodManager.generateFood(position, skin);
        //code
    }

    public Vector2 destroyTail() {
        SnakePoints point = tail.get(tail.size() - 1);
        point.dispose();
        tail.remove(point);
        return point.position;
    }

    public void dispose() {
        texture.dispose();
        for (SnakePoints point : tail) {
            point.dispose();
        }
    }

    //1 - win, 0 - lose
    //snake.pvp(s) 1 - snake, 0 - s
    public boolean pvp(Snake snake){
        for (SnakePoints point : snake.tail){
            if (point.position.dst(this.position) < 60){
                return false;
            }
        }
        return true;
    }

}

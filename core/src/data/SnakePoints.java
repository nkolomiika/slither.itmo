package data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import generators.GeneratorSkin;

public class SnakePoints {

    public Texture texture;
    public TextureRegion textureRegion;
    public Vector2 position;
    public Vector2 nextPosition;
    public Vector2 speed;
    private int velocity = 3;
    public float scale = 1.0f;
    public final float size = 64;
    public final float halfSize = size / 2;
    private final float R = 35;

    public SnakePoints(Vector2 position, Vector2 speed, Colors color) {
        this.position = new Vector2(position);
        this.nextPosition = position;
        Vector2 tmpSpeed = new Vector2(speed);
        this.position = this.position.sub(tmpSpeed.scl(R));
        this.speed = new Vector2(speed);
        this.texture = new Texture(".\\skins\\tails\\" + color.toString().toLowerCase() + ".png");
        this.textureRegion = new TextureRegion(texture);
    }

    public void setSpeed(Vector2 speed, int velocity) {
        this.speed.x = speed.x;
        this.speed.y = speed.y;
        this.velocity = velocity;
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
    }

    public void moveTo() {
        position.lerp(nextPosition, 0.15f);
    }

    public void dispose() {
        texture.dispose();
    }
}

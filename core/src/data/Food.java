package data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import generators.GeneratorPosition;
import generators.GeneratorSize;
import generators.GeneratorSkin;

public class Food {

    public Vector2 position;
    public long id;
    public float point;
    public final float scale = 1f;
    public final float origin = 5f;
    public final float size;
    public final float rotation = 0;
    public Texture texture;
    public TextureRegion textureRegion;

    public Food() {
        this.size = GeneratorSize.generate();
        this.position = GeneratorPosition.generate();
        this.point = size * 2.5f;
        this.texture = new Texture(".\\colors\\" + GeneratorSkin.generate().toString().toLowerCase() + ".png");
        this.textureRegion = new TextureRegion(texture);
    }

    public Food(Vector2 position) {
        this.size = GeneratorSize.generate();
        this.position = position;
        this.point = size * 2.5f;
        this.texture = new Texture(".\\colors\\" + GeneratorSkin.generate().toString().toLowerCase() + ".png");
        this.textureRegion = new TextureRegion(texture);
    }

    public Food(Vector2 position, Colors color) {
        this.size = GeneratorSize.generate();
        this.position = position;
        this.point = size * 2.5f;
        this.texture = new Texture(".\\colors\\" + color.toString().toLowerCase() + ".png");
        this.textureRegion = new TextureRegion(texture);
    }

    public void render(Batch batch) {
        batch.draw(
                textureRegion,
                position.x,
                position.y,
                origin,
                origin,
                size,
                size,
                scale,
                scale,
                rotation
        );
    }

    public void dispose() {
        texture.dispose();
    }

    public void moveTo(Vector2 nextPosition) {
        position.lerp(nextPosition, 0.15f);
    }
}

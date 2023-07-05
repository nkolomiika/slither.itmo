package data.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {

    private final TextureRegion backgroundTexture;
    public Texture texture;

    public Background() {
        texture = new Texture(Gdx.files.internal("background.jpg"));
        backgroundTexture = new TextureRegion(texture, 0, 0, 10000, 6250);
    }

    public void render(Batch batch){
        batch.draw(backgroundTexture, 0, 0);
    }

    public void dispose() {
        texture.dispose();
    }
}

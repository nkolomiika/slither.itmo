package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import data.Snake;

public class MoveManager extends InputAdapter {

    private Vector2 position;

    private boolean leftPressed;
    private boolean rightPressed;
    private boolean spacePressed;
    private boolean upPressed;
    private final float rotateSpeed = 0.05f;

    private final Vector2 direction = new Vector2();

    private final Vector2 mousePosition = new Vector2();

    private int index = 1;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A ||
                keycode == Input.Keys.LEFT) leftPressed = true;
        if (keycode == Input.Keys.D ||
                keycode == Input.Keys.RIGHT) rightPressed = true;
        if (keycode == Input.Keys.SPACE) spacePressed = true;

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A ||
                keycode == Input.Keys.LEFT) leftPressed = false;
        if (keycode == Input.Keys.D ||
                keycode == Input.Keys.RIGHT) rightPressed = false;
        if (keycode == Input.Keys.SPACE) spacePressed = false;

        return false;
    }

    public void updateCameraPosition(Vector2 objPosition) {
        this.position = objPosition;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        int height = Gdx.graphics.getHeight();
        height -= screenY;
        mousePosition.set(screenX, height);
        return false;
    }

    public Vector2 getMousePosition() {
        return mousePosition;
    }

    public void setDirection(Snake head){

        if (!spacePressed)
            head.speedDown();
        else
            head.speedUp();

        if (leftPressed) {
            head.speed.rotateRad(rotateSpeed);
            //direction.set(-1 * speed * index, 0);
        }
        if (rightPressed) {
            head.speed.rotateRad(-1 * rotateSpeed);
            //direction.set(speed * index, 0);
        }
    }
}

package main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import data.Player;
import data.Snake;
import data.sprites.Background;
import generators.GeneratorPosition;
import managers.*;

public class App extends ApplicationAdapter {

    public SpriteBatch batch;
    public OrthographicCamera camera;
    public MoveManager inputProcessor = new MoveManager();
    public Player snake;
    public Background background;
    public FoodManager foodManager;
    public EatManager eatManager;
    public GameManager gameManager;
    public SnakeManager snakeManager;

    @Override
    public void create() {
        Gdx.input.setInputProcessor(inputProcessor);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        snake = new Player();
        camera.position.set(snake.position, 0);
        batch = new SpriteBatch();
        background = new Background();
        foodManager = new FoodManager();
        foodManager.generateFood();
        eatManager = new EatManager(foodManager);
        snakeManager = new SnakeManager(snake);
        snakeManager.generateBots(20);
        gameManager = new GameManager(foodManager, snakeManager);
    }

    @Override
    public void render() {
        inputProcessor.setDirection(snake);
        snakeManager.moveTo(foodManager);

        snakeManager.eat(eatManager);

        ScreenUtils.clear(new Color(Color.DARK_GRAY));

        camera.position.set(snake.position, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        gameManager.run();
        background.render(batch);
        foodManager.render(batch);
        snakeManager.render(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        snake.dispose();
        background.dispose();
        foodManager.dispose();
    }
}

package io.github.projectasteroids.screens;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.projectasteroids.Main;

public class MainMenuScreen implements Screen {
    private final Main game;
    private Stage stage;
    private Texture background;
    private SpriteBatch batch;

    public MainMenuScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background1.jpg")); // Place in assets folder

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json")); // Use LibGDX default skin or your own

        TextButton startButton = new TextButton("Start Game", skin);
        TextButton exitButton = new TextButton("Exit Game", skin);

        startButton.addListener(event -> {
            if (startButton.isPressed()) {
                game.setScreen(new GameScreen(game)); // Replace with your actual game screen
                return true;
            }
            return false;
        });

        exitButton.addListener(event -> {
            if (exitButton.isPressed()) {
                Gdx.app.exit();
                return true;
            }
            return false;
        });

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(startButton).pad(10).row();
        table.add(exitButton).pad(10);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        background.dispose();
    }
}

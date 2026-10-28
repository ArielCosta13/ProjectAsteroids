package io.github.projectasteroids.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.projectasteroids.Main;
import io.github.projectasteroids.assetmanagers.GameAssets;
import io.github.projectasteroids.assetmanagers.graphics.TextureConfig;
import io.github.projectasteroids.components.MovementComponent;
import io.github.projectasteroids.components.RenderLayerComponent;
import io.github.projectasteroids.components.TextureComponent;
import io.github.projectasteroids.components.TransformComponent;
import io.github.projectasteroids.ecsutil.RenderLayer;
import io.github.projectasteroids.systems.MovementSystem;
import io.github.projectasteroids.systems.PlayerInputSystem;
import io.github.projectasteroids.systems.RenderPipelineSystem;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Texture image;
    PooledEngine engine;
    public GameAssets assetsManager;
    private final Main game;
    /**
     *
     */


    public GameScreen(Main game) {this.game = game;}


    @Override
    public void show() {

        engine = new PooledEngine();
        assetsManager = new GameAssets();
        SpriteBatch batch = new SpriteBatch();
        OrthographicCamera camera = new OrthographicCamera(800, 600);

        RenderPipelineSystem renderingSystem = new RenderPipelineSystem(engine,batch, camera);
        MovementSystem movementSystem = new MovementSystem();
        PlayerInputSystem playerInputSystem = new PlayerInputSystem();

        engine.addSystem(renderingSystem);
        engine.addSystem(movementSystem);
        engine.addSystem(playerInputSystem);

        assetsManager.loadTexture("Heavy Weapons Starcruise.png", TextureConfig.hdSmooth());
        image = new Texture("libgdx.png");
        assetsManager.manager.finishLoading();
        Entity bg = engine.createEntity();
        bg.add(new TransformComponent());
        //bg.add(new TextureComponent(new Texture("bg.png")));
        RenderLayerComponent bgLayer = new RenderLayerComponent();
        bgLayer.layer = RenderLayer.BACKGROUND;
        bg.add(bgLayer);
        engine.addEntity(bg);

        Entity player = engine.createEntity();
        TransformComponent pt = new TransformComponent();
        MovementComponent move = new MovementComponent();
        pt.position.set(100, 80);
        pt.z = 0.5f;
        pt.scale.set(1.0f, 1.0f);
        player.add(pt);

        // player.add(new TextureComponent(new Texture("Heavy Weapons Starcruise.png")));
        player.add(new TextureComponent(assetsManager.getTexture("Heavy Weapons Starcruise.png")));
        RenderLayerComponent pl = new RenderLayerComponent();
        pl.layer = RenderLayer.ACTORS;
        player.add(pl);
        player.add(move);
        engine.addEntity(player);
    }

    /**
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        engine.update(Gdx.graphics.getDeltaTime());
    }

    /**
     * @param width
     * @param height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     *
     */
    @Override
    public void pause() {

    }

    /**
     *
     */
    @Override
    public void resume() {

    }

    /**
     *
     */
    @Override
    public void hide() {

    }

    /**
     *
     */
    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

package io.github.projectasteroids.systems;


import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.projectasteroids.components.RenderLayerComponent;
import io.github.projectasteroids.components.TextureComponent;
import io.github.projectasteroids.components.TransformComponent;
import io.github.projectasteroids.ecsutil.RenderLayer;


import java.util.*;

public class RenderPipelineSystem extends EntitySystem {

    private static final ComponentMapper<TransformComponent> tm =
        ComponentMapper.getFor(TransformComponent.class);
    private static final ComponentMapper<TextureComponent> texm =
        ComponentMapper.getFor(TextureComponent.class);
    private static final ComponentMapper<RenderLayerComponent> lm =
        ComponentMapper.getFor(RenderLayerComponent.class);

    private final SpriteBatch batch;
    private final OrthographicCamera camera;

    private final ImmutableArray<Entity> entities;

    public RenderPipelineSystem(Engine engine, SpriteBatch batch, OrthographicCamera camera) {
        this.batch = batch;
        this.camera = camera;

        this.entities = engine.getEntitiesFor(
            Family.all(
                TransformComponent.class,
                TextureComponent.class,
                RenderLayerComponent.class
            ).get()
        );
    }

    @Override
    public void update(float deltaTime) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        // Group entities by layer
        Map<RenderLayer, List<Entity>> layers = new EnumMap<>(RenderLayer.class);
        for (RenderLayer layer : RenderLayer.values()) {
            layers.put(layer, new ArrayList<>());
        }

        for (Entity e : entities) {
            RenderLayerComponent r = lm.get(e);
            if (r.visible) {
                layers.get(r.layer).add(e);
            }
        }

        batch.begin();

        // Render each layer in order
        for (RenderLayer layer : RenderLayer.values()) {
            List<Entity> list = layers.get(layer);

            // Sort by Z inside the layer
            list.sort((a, b) -> Float.compare(
                tm.get(a).z,
                tm.get(b).z
            ));

            // Render entities
            for (Entity e : list) {
                renderEntity(e);
            }
        }

        batch.end();
    }

    private void renderEntity(Entity entity) {
        TransformComponent t = tm.get(entity);
        TextureComponent tex = texm.get(entity);

        if (tex.region == null) return;

        batch.draw(
            tex.region,
            t.position.x,
            t.position.y,
            tex.centreX,
            tex.centreY,
            tex.region.getRegionWidth(),
            tex.region.getRegionHeight(),
            t.scale.x,
            t.scale.y,
            t.rotation
        );
    }
}

package io.github.projectasteroids.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.projectasteroids.components.MovementComponent;

public class PlayerInputSystem extends EntitySystem {

    private final ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);

    @Override
    public void update(float delta) {
        for (Entity e : getEngine().getEntitiesFor(Family.all(MovementComponent.class).get())) {
            MovementComponent m = mm.get(e);

            m.thrusting = Gdx.input.isKeyPressed(Input.Keys.W);
            m.turnLeft  = Gdx.input.isKeyPressed(Input.Keys.A);
            m.turnRight = Gdx.input.isKeyPressed(Input.Keys.D);
        }
    }
}

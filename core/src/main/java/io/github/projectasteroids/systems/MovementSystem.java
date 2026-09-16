package io.github.projectasteroids.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import io.github.projectasteroids.components.MovementComponent;
import io.github.projectasteroids.components.TransformComponent;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent t = tm.get(entity);
        MovementComponent m = mm.get(entity);

        // Linear motion
        m.velocity.mulAdd(m.acceleration, deltaTime);
        t.position.mulAdd(m.velocity, deltaTime);

        // Angular motion
        t.rotation += m.angularVelocity * deltaTime;
    }
}

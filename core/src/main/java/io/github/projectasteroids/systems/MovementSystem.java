package io.github.projectasteroids.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import io.github.projectasteroids.components.MovementComponent;
import io.github.projectasteroids.components.TransformComponent;

public class MovementSystem extends IteratingSystem {

    private final ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<MovementComponent> mm = ComponentMapper.getFor(MovementComponent.class);

    public MovementSystem() {
        super(Family.all(TransformComponent.class, MovementComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float delta) {

        TransformComponent t = tm.get(entity);
        MovementComponent m = mm.get(entity);

        // --- ANGULAR ACCELERATION ---
        float angularAcc = 0f;

        if (m.turnLeft)  angularAcc += m.turnAcceleration;
        if (m.turnRight) angularAcc -= m.turnAcceleration;

        // Apply angular acceleration
        m.angularVelocity += angularAcc * delta;

        // Apply angular drag
        if (angularAcc == 0f) {
            m.angularVelocity *= (1f - m.angularInertia);
        }

        // Clamp angular velocity
        m.angularVelocity = MathUtils.clamp(
            m.angularVelocity,
            -m.maxAngularVelocity,
            m.maxAngularVelocity
        );

        // Apply rotation
        t.rotation += m.angularVelocity * delta;


        // --- LINEAR ACCELERATION (THRUST IN FACING DIRECTION) ---
        if (m.thrusting) {
            float rad = MathUtils.degRad * t.rotation;
            float ax = MathUtils.cos(rad) * m.thrust;
            float ay = MathUtils.sin(rad) * m.thrust;

            m.velocity.x += ax * delta;
            m.velocity.y += ay * delta;
        } else {
            // Apply linear drag
            m.velocity.scl(1f - m.inertia);
        }

        // Clamp max speed
        if (m.velocity.len2() > m.maxSpeed * m.maxSpeed) {
            m.velocity.nor().scl(m.maxSpeed);
        }

        // Apply velocity to position
        t.position.x += m.velocity.x * delta;
        t.position.y += m.velocity.y * delta;
    }
}

package io.github.projectasteroids.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class MovementComponent implements Component {
    public final Vector2 velocity = new Vector2();      // px/sec
    public final Vector2 acceleration = new Vector2();  // px/sec^2
    public float angularVelocity = 0f;                  // deg/sec
}


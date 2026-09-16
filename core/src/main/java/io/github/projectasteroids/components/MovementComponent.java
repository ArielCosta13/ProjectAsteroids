package io.github.projectasteroids.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class MovementComponent implements Component {

    // Linear motion
    public final Vector2 velocity = new Vector2();
    public float maxSpeed = 300f;

    // Angular motion
    public float angularVelocity = 0f;
    public float maxAngularVelocity = 180f; // degrees/sec

    // Forces
    public float thrust = 600f;          // forward acceleration
    public float turnAcceleration = 200f; // degrees/sec^2

    // Drag
    public float inertia = 0.10f;        // linear drag
    public float angularInertia = 0.10f; // rotational drag

    // Input flags
    public boolean thrusting;
    public boolean turnLeft;
    public boolean turnRight;
}


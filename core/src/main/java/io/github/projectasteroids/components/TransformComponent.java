package io.github.projectasteroids.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent implements Component {
    public final Vector2 position = new Vector2();
    public float rotation = 0f;      // degrees
    public final Vector2 scale = new Vector2(1f, 1f);
    public float z = 0f;             // for sorting if needed
}

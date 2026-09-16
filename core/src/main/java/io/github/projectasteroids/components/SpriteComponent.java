package io.github.projectasteroids.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteComponent implements Component {
    public TextureRegion region = null;

    // Optional: color tint, flip flags, etc.
    public float originX = 0f;
    public float originY = 0f;
}

package io.github.projectasteroids.components;


import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent implements Component {
    public TextureRegion region;
    public float centreX = 0f;
    public float centreY = 0f;

    public TextureComponent() {}

    public TextureComponent(Texture texture) {

        this.region = new TextureRegion(texture);
        centreX = (float) texture.getWidth() / 2;
        centreY =  (float) texture.getHeight() / 2;
        //to avoid


    }
}


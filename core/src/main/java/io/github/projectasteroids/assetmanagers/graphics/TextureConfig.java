package io.github.projectasteroids.assetmanagers.graphics;

import com.badlogic.gdx.graphics.Texture;

public class TextureConfig {
    public boolean useMipMaps = true;
    public Texture.TextureFilter minFilter = Texture.TextureFilter.MipMapLinearLinear;
    public Texture.TextureFilter magFilter = Texture.TextureFilter.Linear;
    public float anisotropy = 8f;

    public static TextureConfig hdSmooth() {
        TextureConfig cfg = new TextureConfig();
        cfg.useMipMaps = true;
        cfg.minFilter = Texture.TextureFilter.MipMapLinearLinear;
        cfg.magFilter = Texture.TextureFilter.Linear;
        cfg.anisotropy = 8f;
        return cfg;
    }

    public static TextureConfig pixelArt() {
        TextureConfig cfg = new TextureConfig();
        cfg.useMipMaps = false;
        cfg.minFilter = Texture.TextureFilter.Nearest;
        cfg.magFilter = Texture.TextureFilter.Nearest;
        cfg.anisotropy = 0f;
        return cfg;
    }
}

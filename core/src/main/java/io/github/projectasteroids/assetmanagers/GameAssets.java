package io.github.projectasteroids.assetmanagers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import io.github.projectasteroids.assetmanagers.graphics.TextureAssetLoader;
import io.github.projectasteroids.assetmanagers.graphics.TextureConfig;

public class GameAssets {
    public final AssetManager manager;

    public GameAssets() {
        manager = new AssetManager();
        manager.setLoader(Texture.class, new TextureAssetLoader(new InternalFileHandleResolver()));
    }

    public void loadTexture(String path, TextureConfig cfg) {
        TextureAssetLoader.TextureParameter params = new TextureAssetLoader.TextureParameter();
        params.config = cfg;
        manager.load(path, Texture.class, params);
    }

    public Texture getTexture(String path) {
        return manager.get(path, Texture.class);
    }
}

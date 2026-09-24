package io.github.projectasteroids.assetmanagers.graphics;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class TextureAssetLoader extends AsynchronousAssetLoader<Texture, TextureAssetLoader.TextureParameter> {

    public static class TextureParameter extends AssetLoaderParameters<Texture> {
        public TextureConfig config;
    }

    public TextureAssetLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, TextureParameter parameter) {}

    @Override
    public Texture loadSync(AssetManager manager, String fileName, FileHandle file, TextureParameter parameter) {
        TextureConfig cfg = parameter.config;

        Texture texture = new Texture(file, cfg.useMipMaps);
        texture.setFilter(cfg.minFilter, cfg.magFilter);

        if (cfg.anisotropy > 0f) {
            texture.setAnisotropicFilter(cfg.anisotropy);
        }

        return texture;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, TextureParameter parameter) {
        return null;
    }
}

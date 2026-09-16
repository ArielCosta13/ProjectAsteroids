package io.github.projectasteroids.components;


import com.badlogic.ashley.core.Component;
import io.github.projectasteroids.ecsutil.RenderLayer;


public class RenderLayerComponent implements Component {
    public RenderLayer layer = RenderLayer.WORLD;
    public boolean visible = true;
}

package com.story.core.entities.map;

import com.story.modules.dbdata.descriptor.MapDescriptor;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public abstract class MapEntity implements IMapEntity {
    public static final String BackgroundLayerName = "Background";
    public static final String ObjectsLayerName = "Objects";
    public static final String BorderLayerName = "Border";
    public static final String MarginPropertyName = "count";

    protected MapDescriptor mapDescriptor = null;
    protected TiledMap tiledMap = null;
    protected Point mapPosition = null;

    public MapEntity(MapDescriptor map){
        this.mapDescriptor = map;
        this.mapPosition = new Point(0, 0);
    }

    public TiledMap getTiledMap(){
        return this.tiledMap;
    }

    public Point getMapPosition(){
        return this.mapPosition;
    }
}

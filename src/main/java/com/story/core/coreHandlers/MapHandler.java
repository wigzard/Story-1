package com.story.core.coreHandlers;

import com.story.core.frames.IFrameBuilder;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public abstract class MapHandler implements IFrameBuilder{
    public static final String BackgroundLayerName = "Background";
    public static final String ObjectsLayerName = "Objects";
    public static final String BorderLayerName = "Border";
    public static final String MarginPropertyName = "count";

    protected MapDescriptor mapDescriptor = null;
    protected TiledMap tiledMap = null;
    protected Point mapPosition = null;

    public MapHandler(MapDescriptor map){
        this.mapDescriptor = map;
        this.mapPosition = new Point(0, 0);
    }

    public MapHandler(MapDescriptor map, Point objectPosition){
        this.mapDescriptor = map;
        this.mapPosition = new Point(objectPosition.x, objectPosition.y);
    }

    public TiledMap getTiledMap(){
        return this.tiledMap;
    }

    public Point getMapPosition(){
        return this.mapPosition;
    }

    public abstract boolean isCanMove(Point object);
    public abstract void setCenterObject(Point centerObject);
    public abstract int getMargin();
    public abstract void init() throws SlickException;
}

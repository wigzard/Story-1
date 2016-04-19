package com.story.core.baseHandlers;

import com.story.modules.dbdata.view.MapData;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public abstract class MapHandler implements IHandler{
    public static final String BackgroundLayerName = "Background";
    public static final String ObjectsLayerName = "Objects";
    public static final String BorderLayerName = "Border";
    public static final String MarginPropertyName = "count";

    protected MapData mapData = null;
    protected TiledMap tiledMap = null;
    protected Point mapPosition = null;

    public MapHandler(MapData map){
        this.mapData = map;
        this.mapPosition = new Point(0, 0);
    }

    public MapHandler(MapData map, Point objectPosition){
        this.mapData = map;
        this.mapPosition = new Point(objectPosition.x, objectPosition.y);
    }

    public TiledMap getTiledMap(){
        return this.tiledMap;
    }

    public Point getMapPosition(){
        return this.mapPosition;
    }

    public abstract boolean isCanMove(Point object);
    public abstract void setCoordinates(Point centerObject);
    public abstract int getMargin();
}

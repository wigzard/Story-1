package com.story.core.baseHandlers;

import com.story.core.GlobalVar;
import com.story.modules.dbdata.view.MapData;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;

/**
 * Created by alex on 14.04.16.
 */
public class BaseMapHandler extends MapHandler {
    private int borderLayer = -1;
    private int objectLayer = -1;

    public BaseMapHandler(MapData map) throws SlickException {
        super(map);
    }

    public BaseMapHandler(MapData map, Point objectPosition) throws SlickException {
        super(map, objectPosition);
        this.init();
    }

    @Override
    public boolean isCanMove(Point objectPosition) {
        objectPosition.x += this.getMargin();
        objectPosition.y += this.getMargin();

        return this.tiledMap.getTileId(objectPosition.x, objectPosition.y, borderLayer) == 0 &&
                this.tiledMap.getTileId(objectPosition.x, objectPosition.y, objectLayer) == 0;
    }

    @Override
    public void setCoordinates(Point centerObject) {
        int tileWidth = this.tiledMap.getTileWidth();
        int tileHeight = this.tiledMap.getTileHeight();

        centerObject.x += this.getMargin();
        centerObject.y += this.getMargin();

        this.mapPosition.x = 0;
        this.mapPosition.y = 0;

        this.mapPosition.x -= ((centerObject.x * tileWidth - tileWidth / 2) - GlobalVar.Width / 2) / tileWidth;
        this.mapPosition.y -= ((centerObject.y * tileHeight - tileHeight / 2) - GlobalVar.Height / 2) / tileHeight;
    }

    @Override
    public int getMargin() {
        String margin = this.tiledMap.getLayerProperty(this.tiledMap.getLayerIndex(BorderLayerName),
                MarginPropertyName, "0");
        return Integer.valueOf(margin);
    }

    @Override
    public void init() throws SlickException {
        this.tiledMap = new TiledMap(this.mapData.getPathToTMX());

        this.borderLayer = this.tiledMap.getLayerIndex(BorderLayerName);
        this.objectLayer = this.tiledMap.getLayerIndex(ObjectsLayerName);
    }
}

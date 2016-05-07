package com.story.game.handlers;

import com.story.modules.global.Converter;
import com.story.core.coreHandlers.MapHandler;
import com.story.core.frames.CentralObject;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.util.*;

/**
 * Created by alex on 14.04.16.
 */
public class BaseMapHandler extends MapHandler {
    private int borderLayer = -1;
    private int objectLayer = -1;

    public BaseMapHandler(MapDescriptor map) throws SlickException {
        super(map);
    }

    public BaseMapHandler(MapDescriptor map, Point objectPosition) throws SlickException {
        super(map, objectPosition);
        this.init();
    }

    @Override
    public boolean isCanMove(Point objectPosition) {
        Point temp = new Point(objectPosition);

        temp.x += this.getMargin();
        temp.y += this.getMargin();

        return this.tiledMap.getTileId(temp.x, temp.y, borderLayer) == 0 &&
                this.tiledMap.getTileId(temp.x, temp.y, objectLayer) == 0;
    }

    @Override
    public void setCenterObject(Point centerObject) {
        this.mapPosition.x = 0;
        this.mapPosition.y = 0;
        this.mapPosition.x -= centerObject.x;
        this.mapPosition.y -= centerObject.y;
    }

    @Override
    public int getMargin() {
        String margin = this.tiledMap.getLayerProperty(this.tiledMap.getLayerIndex(BorderLayerName),
                MarginPropertyName, "0");
        return Integer.valueOf(margin);
    }

    @Override
    public void init() throws SlickException {
        this.tiledMap = new TiledMap(this.mapDescriptor.getPathToTMX());

        this.borderLayer = this.tiledMap.getLayerIndex(BorderLayerName);
        this.objectLayer = this.tiledMap.getLayerIndex(ObjectsLayerName);
    }

    /**
     *
     * @param start start position on the map
     * @param end end position on the map
     * @return frame queue for render
     */
    @Override
    public Queue<CentralObject> buildFrames(Point start, Point end) {
        if (start.equals(end)){
            return new LinkedList<>();
        }

        start = Converter.ObjectPositionToMapPosition(start,
                this.getTiledMap().getTileWidth(),
                this.tiledMap.getTileHeight(),
                this.getMargin());
        end = Converter.ObjectPositionToMapPosition(end,
                this.getTiledMap().getTileWidth(),
                this.tiledMap.getTileHeight(),
                this.getMargin());

        Queue<CentralObject> framesQueue = new LinkedList<>();

        int steps = 6;
        int stepX = start.x - end.x == 0? 0 : (end.x - start.x) / steps;
        int stepY = start.y - end.y == 0? 0 : (end.y - start.y) / steps;

        for (int i = 0; i < steps; i++){
            framesQueue.add(new CentralObject(new Point(((start.x + stepX * (i + 1))),
                    (start.y + stepY * (i + 1)))));
        }
        framesQueue.add(new CentralObject(end));

        return framesQueue;
    }
}

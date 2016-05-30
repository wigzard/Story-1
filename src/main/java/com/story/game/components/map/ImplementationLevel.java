package com.story.game.components.map;

import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.global.Converter;
import com.story.utils.frames.*;
import com.story.utils.frames.Frame;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by alex on 23.05.16.
 */
abstract class ImplementationLevel extends AbstractMap {
    int borderLayer = -1;
    int objectLayer = -1;

    ImplementationLevel(MapDescriptor map) {
        super(map);
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

    /**
     * @return The margin of current map
     */
    @Override
    public int getMargin() {
        String margin = this.tiledMap.getLayerProperty(this.tiledMap.getLayerIndex(BorderLayerName),
                MarginPropertyName, "0");
        return Integer.valueOf(margin);
    }

    /**
     * Calculate a global coordinate of target object
     * @param target map coordinates
     * @return global coordinate
     */
    @Override
    public Point getGlobalCoordinate(Point target) {
        Point p = new Point();

        p.x = (target.x + this.getMargin()
                + this.getMapPosition().x / this.getTiledMap().getTileWidth())
                * this.getTiledMap().getTileWidth()
                + this.getMapPosition().x % this.getTiledMap().getTileWidth();
        p.y = (target.y + this.getMargin()
                + this.getMapPosition().y / this.getTiledMap().getTileHeight())
                * this.getTiledMap().getTileHeight()
                + this.getMapPosition().y % this.getTiledMap().getTileWidth();

        return p;
    }

    /**
     * Build frames for player object on the map
     * @param start the point of start
     * @param end the point of end
     * @return the queue of frames
     */
    @Override
    protected Queue<Frame> buildPlayerFrames(Point start, Point end){
        if (start.equals(end)){
            return null;
        }

        if (!this.isCanMove(end)){
            return null;
        }

        Point s = Converter.ObjectPositionToMapPosition(start,
                this.getTiledMap().getTileWidth(),
                this.getTiledMap().getTileHeight(),
                this.getMargin());
        Point e = Converter.ObjectPositionToMapPosition(end,
                this.getTiledMap().getTileWidth(),
                this.getTiledMap().getTileHeight(),
                this.getMargin());

        Queue<Frame> framesQueue = new LinkedList<>();

        int steps = 6;
        int stepX = s.x - e.x == 0? 0 : (e.x - s.x) / steps;
        int stepY = s.y - e.y == 0? 0 : (e.y - s.y) / steps;

        for (int i = 0; i < steps; i++){
            framesQueue.add(new Frame(new Point(((s.x + stepX * (i + 1))),
                    (s.y + stepY * (i + 1)))));
        }
        framesQueue.add(new Frame(e));

        return framesQueue;
    }
}

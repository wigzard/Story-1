package com.story.game.components;

import com.story.core.entities.map.MapEntity;
import com.story.core.entities.Npc;
import com.story.utils.frames.Frame;
import com.story.game.factories.AnimationFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.global.Converter;
import com.story.utils.frames.IFrameStorage;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.*;

/**
 * Created by alex on 14.05.16.
 */
public class SimpleNpcComponent extends Npc {
    public SimpleNpcComponent(PersonDescriptor person, Point startPoint) throws SlickException {
        super(person, startPoint);
    }

    private Point move(Direction d, Point p) {
        switch (d){
            case DOWN:
                p.y++;
                break;
            case UP:
                p.y--;
                break;
            case RIGHT:
                p.x++;
                break;
            case LEFT:
                p.x--;
                break;
        }
        return p;
    }

    @Override
    public void move(Direction d, MapEntity mapHandler, IFrameStorage frameStorage) {
        Point startPoint = this.getCurrentPosition();
        Point endPoint = this.move(d, this.getCurrentPosition());
        this.setCurrentDirection(d);

        if ((!mapHandler.isCanMove(endPoint))){
            return;
        }

        this.currentPosition = this.move(d, this.currentPosition);
        frameStorage.addFrames(this.buildFrames(mapHandler, startPoint, endPoint));
    }

    @Override
    public void init() throws SlickException {
        this.moveAnimation = new HashMap<>();
        this.moveAnimation.put(Direction.DOWN,
                AnimationFactory.createSimpleNPCAnimation(Direction.DOWN, this.descriptor));
        this.moveAnimation.put(Direction.LEFT,
                AnimationFactory.createSimpleNPCAnimation(Direction.LEFT, this.descriptor));
        this.moveAnimation.put(Direction.RIGHT,
                AnimationFactory.createSimpleNPCAnimation(Direction.RIGHT, this.descriptor));
        this.moveAnimation.put(Direction.UP,
                AnimationFactory.createSimpleNPCAnimation(Direction.UP, this.descriptor));
    }

    @Override
    public void setCurrentDirection(Direction d) {
        this.currentDirection = d;
    }

    /**
     * Create a frames
     * @param start start position on the map
     * @param end end position on the map
     * @return frame queue for render
     */
    @Override
    public Queue<Frame> buildFrames(MapEntity map, Point start, Point end) {
        if (start.equals(end)){
            return new LinkedList<>();
        }

        start = Converter.ObjectPositionToMapPosition(start,
                map.getTiledMap().getTileWidth(),
                map.getTiledMap().getTileHeight(),
                map.getMargin());
        end = Converter.ObjectPositionToMapPosition(end,
                map.getTiledMap().getTileWidth(),
                map.getTiledMap().getTileHeight(),
                map.getMargin());

        Queue<Frame> framesQueue = new LinkedList<>();

        int steps = 6;
        int stepX = start.x - end.x == 0? 0 : (end.x - start.x) / steps;
        int stepY = start.y - end.y == 0? 0 : (end.y - start.y) / steps;

        for (int i = 0; i < steps; i++){
            framesQueue.add(new Frame(new Point(((start.x + stepX * (i + 1))),
                    (start.y + stepY * (i + 1)))));
        }
        framesQueue.add(new Frame(end));

        return framesQueue;
    }

    /**
     * Calculate coordinate on the map
     * @param map current map handler
     * @param object object map coordinate
     * @return global coordinate
     */
    @Override
    public Point calculateCoordinates(MapEntity map, Point object) {
        return map.getGlobalCoordinate(object);
    }
}

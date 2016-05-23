package com.story.game.components;

import com.story.core.entities.map.MapEntity;
import com.story.core.entities.Player;
import com.story.core.frames.*;
import com.story.core.frames.Frame;
import com.story.game.factories.AnimationFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.global.Converter;
import com.story.modules.global.GlobalVar;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.*;

/**
 * Created by alex on 16.04.16.
 */
public class PlayerComponent extends Player {
    public PlayerComponent(PersonDescriptor person) throws SlickException {
        super(person);
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

    /**
     * Move the player
     * @param d direction of move
     * @param mapHandler map handler
     * @param frameStorage frame storage
     */
    @Override
    public void move(Direction d, MapEntity mapHandler, IFrameStorage frameStorage) {
        Point startPoint = this.getCurrentPosition();
        Point endPoint = this.move(d, this.getCurrentPosition());

        if ((!mapHandler.isCanMove(endPoint))
                || (frameStorage.hasNextFrame())){
            return;
        }

        this.setCurrentDirection(d);
        this.currentPosition = this.move(d, this.currentPosition);
        frameStorage.addFrames(this.buildFrames(mapHandler, startPoint, endPoint));
    }

    @Override
    public void init() throws SlickException {
        this.moveAnimation = new HashMap<>();
        this.moveAnimation.put(Direction.DOWN,
                AnimationFactory.createPlayerAnimation(Direction.DOWN, this.descriptor));
        this.moveAnimation.put(Direction.LEFT,
                AnimationFactory.createPlayerAnimation(Direction.LEFT, this.descriptor));
        this.moveAnimation.put(Direction.RIGHT,
                AnimationFactory.createPlayerAnimation(Direction.RIGHT, this.descriptor));
        this.moveAnimation.put(Direction.UP,
                AnimationFactory.createPlayerAnimation(Direction.UP, this.descriptor));
    }

    @Override
    public void setCurrentDirection(Direction d) {
        this.currentDirection = d;
    }

    /**
     * Create the central frames
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
     * Calculate coordinate on map for object
     * @param object tile position on map
     * @return global coordinates on map
     */
    @Override
    public Point calculateCoordinates(MapEntity map, Point object) {
        int countTileOfWidth = GlobalVar.Width / 2 / map.getTiledMap().getTileWidth()
                * map.getTiledMap().getTileWidth();
        int countTileOfHeight = GlobalVar.Height / 2 / map.getTiledMap().getTileHeight()
                * map.getTiledMap().getTileHeight();

        Point p = new Point();
        p.x = map.getTiledMap().getTileWidth() + countTileOfWidth;
        p.y = map.getTiledMap().getTileHeight() + countTileOfHeight;

        return p;
    }
}

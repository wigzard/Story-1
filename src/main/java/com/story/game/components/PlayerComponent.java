package com.story.game.components;

import com.story.core.entities.map.MapEntity;
import com.story.core.entities.Player;
import com.story.game.callbacks.arguments.MoveArgs;
import com.story.modules.global.ActionType;
import com.story.game.factories.AnimationFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
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

    private Point move(Direction d, Point p){
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
     * @param d The direction of move
     */
    @Override
    public void move(Direction d) {
        Point startPoint = this.getCurrentPosition();
        Point endPoint = this.move(d, this.getCurrentPosition());

        this.setCurrentDirection(d);
        try {
            boolean result = false;
            if (this.callbackList.containsKey(ActionType.MOVE)) {
                MoveArgs args = new MoveArgs(startPoint, endPoint);
                result = this.callbackList.get(ActionType.MOVE).call(args);
            }

            if (!result){
                return;
            }

            this.currentPosition = this.move(d, this.currentPosition);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

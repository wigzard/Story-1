package com.story.game.handlers;

import com.story.core.coreHandlers.MapHandler;
import com.story.core.coreHandlers.PlayerHandler;
import com.story.core.frames.IFrameStorage;
import com.story.game.factories.AnimationFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by alex on 16.04.16.
 */
public class BasePlayerHandler extends PlayerHandler {
    public BasePlayerHandler(PersonDescriptor person) throws SlickException {
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
    public void move(Direction d, MapHandler mapHandler, IFrameStorage frameStorage) {
        Point startPoint = this.getCurrentPosition();
        Point endPoint = this.move(d, this.getCurrentPosition());

        if ((!mapHandler.isCanMove(endPoint))
                || (frameStorage.hasNextFrameOfCentralObject())){
            return;
        }

        this.setCurrentDirection(d);
        this.currentPosition = this.move(d, this.currentPosition);
        frameStorage.addCentralObjectFrames(mapHandler.buildFrames(startPoint, endPoint));
    }

    @Override
    public void init() throws SlickException {
        this.moveAnimation = new HashMap<>();
        this.moveAnimation.put(Direction.DOWN,
                AnimationFactory.createPlayerAnimation(Direction.DOWN, this.playerDescriptor));
        this.moveAnimation.put(Direction.LEFT,
                AnimationFactory.createPlayerAnimation(Direction.LEFT, this.playerDescriptor));
        this.moveAnimation.put(Direction.RIGHT,
                AnimationFactory.createPlayerAnimation(Direction.RIGHT, this.playerDescriptor));
        this.moveAnimation.put(Direction.UP,
                AnimationFactory.createPlayerAnimation(Direction.UP, this.playerDescriptor));
    }

    @Override
    public void setCurrentDirection(Direction d) {
        this.curreDirection = d;
    }
}

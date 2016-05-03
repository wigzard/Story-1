package com.story.core.baseHandlers;

import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by alex on 09.04.16.
 */
public abstract class PlayerHandler{
    public enum Direction{UP, LEFT, DOWN, RIGHT}

    protected PersonDescriptor playerDescriptor = null;
    protected Point currentPosition = null;
    protected HashMap<Direction, Animation> moveAnimation = null;
    protected Direction curreDirection;

    public PlayerHandler(PersonDescriptor person) throws SlickException {
        this.playerDescriptor = person;
        this.currentPosition = new Point(0, 0);
        this.curreDirection = Direction.DOWN;
    }

    public Point getCurrentPosition(){
        return new Point(this.currentPosition);
    }

    public Animation getMoveAnimation(){
        return this.moveAnimation.get(this.curreDirection);
    }

    public abstract void move(Direction d);
    public abstract void init() throws SlickException;
    public abstract void setCurrentDirection(Direction d);
}

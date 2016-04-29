package com.story.core.baseHandlers;

import com.story.core.frames.IFrameBuilder;
import com.story.modules.dbdata.view.PersonDescriptor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public abstract class PlayerHandler{
    public enum Direction{UP, LEFT, DOWN, RIGHT}

    protected PersonDescriptor playerData = null;
    protected Point currentPosition = null;
    protected SpriteSheet playerSheet = null;
    protected Animation moveAnimation = null;

    public PlayerHandler(PersonDescriptor person) throws SlickException {
        this.playerData = person;
        this.currentPosition = new Point(0, 0);
    }

    public Point getCurrentPosition(){
        return new Point(this.currentPosition);
    }

    public Animation getMoveAnimation(){
        return this.moveAnimation;
    }

    public abstract void move(Direction d);
    public abstract void init() throws SlickException;
}

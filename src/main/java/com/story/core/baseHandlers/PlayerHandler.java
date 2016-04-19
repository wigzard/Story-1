package com.story.core.baseHandlers;

import com.story.modules.dbdata.view.PersonData;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public abstract class PlayerHandler implements IHandler {
    public enum Direction{UP, LEFT, DOWN, RIGHT}

    protected PersonData player = null;
    protected Point currentPosition = null;

    public PlayerHandler(PersonData person)
    {
        this.player = person;
        this.currentPosition = new Point(0, 0);
    }

    public Point getCurrentPosition(){
        return new Point(this.currentPosition);
    }

    public abstract void move(Direction d);
}

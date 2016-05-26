package com.story.core.entities;

import com.story.core.entities.map.MapEntity;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.utils.frames.IFrameBuilder;
import com.story.utils.frames.IFrameStorage;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.*;

/**
 * Created by alex on 14.05.16.
 */
public abstract class PersonEntity implements IFrameBuilder, IEntity {
    public enum Direction{UP, LEFT, DOWN, RIGHT}

    protected PersonDescriptor descriptor = null;
    protected Point currentPosition = null;
    protected HashMap<Direction, Animation> moveAnimation = null;
    protected Direction currentDirection;

    public PersonEntity(PersonDescriptor person, Point startPosition) throws SlickException {
        this.descriptor = person;
        this.currentPosition = startPosition;
        this.currentDirection = Direction.DOWN;
    }

    public Point getCurrentPosition(){
        return new Point(this.currentPosition);
    }

    public Animation getMoveAnimation(){
        return this.moveAnimation.get(this.currentDirection);
    }

    public abstract void move(Direction d, MapEntity mapHandler, IFrameStorage frameStorage);
    public abstract void setCurrentDirection(Direction d);
    public abstract Point calculateCoordinates(MapEntity map, Point object);
}

package com.story.core.entities.map;

import com.story.core.entities.IEntity;

import java.awt.*;

/**
 * Created by alex on 23.05.16.
 */
public interface IMapEntity extends IEntity {
    public abstract boolean isCanMove(Point object);
    public abstract void setCenterObject(Point centerObject);
    public abstract int getMargin();
    public abstract Point getGlobalCoordinate(Point target);
}

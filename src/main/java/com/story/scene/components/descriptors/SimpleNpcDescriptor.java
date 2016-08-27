package com.story.scene.components.descriptors;

import java.awt.*;

/**
 * Created by alex on 06.08.16.
 * Represent the data for simple npc on map
 */
public class SimpleNpcDescriptor extends BaseActorDescriptor {
    private static final int DefaultMoveInterval = 5000;

    /**
     * The start position of npc
     */
    private Point startPosition;

    /**
     * The interval of move npc by map
     */
    private int moveInterval;

    /**
     * Initialize new instance of {@link SimpleNpcDescriptor}
     * @param id the id of npc
     */
    public SimpleNpcDescriptor(int id) {
        super(id);
        this.moveInterval = DefaultMoveInterval;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public void dispose() {
        super.dispose();

        this.startPosition = null;
    }

    public int getMoveInterval() {
        return moveInterval;
    }

    public void setMoveInterval(int moveInterval) {
        this.moveInterval = moveInterval;
    }
}

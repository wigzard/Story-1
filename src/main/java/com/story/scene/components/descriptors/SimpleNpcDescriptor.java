package com.story.scene.components.descriptors;

import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 06.08.16.
 * Represent the data for simple npc on map
 */
public class SimpleNpcDescriptor extends BaseActorDescriptor {

    /**
     * The start position of npc
     */
    private Point startPosition;

    /**
     * Initialize new instance of {@link SimpleNpcDescriptor}
     * @param id the id of npc
     */
    public SimpleNpcDescriptor(int id) {
        super(id);
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
}

package com.story.scene.components.descriptors;

import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 23.07.16.
 */
public class PlayerDescriptor extends BaseActorDescriptor {
    private Point centerPosition;
    private Point startPosition;

    public PlayerDescriptor(int playerId) {
        super(playerId);
    }

    public Point getCenterPosition() {
        return centerPosition;
    }

    public void setCentralPoint(Point centerPosition){
        this.centerPosition = centerPosition;
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

        this.centerPosition = null;
    }
}

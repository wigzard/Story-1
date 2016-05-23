package com.story.core.frames;

import java.awt.*;

/**
 * Created by alex on 28.04.16.
 */
public class Frame {
    private Point position;

    public Frame(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
}

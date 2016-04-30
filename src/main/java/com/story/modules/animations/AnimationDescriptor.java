package com.story.modules.animations;

import java.awt.*;

/**
 * Created by alex on 29.04.16.
 */
public class AnimationDescriptor {
    private String path;
    private int tileWidth = 32;
    private int tileHeight = 32;
    private int countOfFrames = 0;
    private int duration = 100;
    private Point startPosition = null;
    private boolean autoUpdate = false;

    public AnimationDescriptor(){
        this.startPosition = new Point();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileSize(int width, int height) {
        this.tileWidth = width;
        this.tileHeight = height;
    }

    public void setCountOfFrames(int countOfFrames) {
        this.countOfFrames = countOfFrames;
    }

    public int getCountOfFrames() {
        return countOfFrames;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }
}

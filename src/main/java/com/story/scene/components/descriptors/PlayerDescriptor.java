package com.story.scene.components.descriptors;

import com.story.system.IDisposable;
import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 23.07.16.
 */
public class PlayerDescriptor implements IDisposable {
    private Point centerPosition;
    private Size tileSize;
    private String url;
    private Point startPosition;

    public PlayerDescriptor() {
    }

    public Point getCenterPosition() {
        return centerPosition;
    }

    @Override
    public void dispose() {
        this.centerPosition = null;
        this.tileSize = null;
    }

    public Size getTileSize() {
        return tileSize;
    }

    public void setCentralPoint(Point centerPosition){
        this.centerPosition = centerPosition;
    }

    public void setTileSize(Size tileSize){
        this.tileSize = tileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }
}

package com.story.scene.components.descriptors;

import com.story.scene.components.helpers.MapViewer;
import com.story.system.IDisposable;
import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 16.07.16.
 * Class describe the start state of {@link MapViewer}
 */
public class ViewerDescriptor implements IDisposable {

    /**
     * Start coordinates on map
     */
    private Point startCoordinates;

    /**
     * Size of tile
     */
    private Size tileSize;

    /**
     * Size of map
     */
    private Size mapSize;

    /**
     * The size of screen
     */
    private Size screenSize;

    /**
     * Initialize the instance of {@link ViewerDescriptor}
     */
    public ViewerDescriptor(){
        this.startCoordinates = new Point();
    }

    public Point getStartCoordinates() {
        return startCoordinates;
    }

    public void setStartCoordinates(Point startCoordinates) {
        this.startCoordinates = startCoordinates;
    }

    public Size getTileSize() {
        return tileSize;
    }

    public void setTileSize(Size tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Gets count of tiles on map
     * @return size of map
     */
    public Size getMapSize() {
        return mapSize;
    }

    public void setMapSize(Size mapSize) {
        this.mapSize = mapSize;
    }

    public Size getScreenSize() {
        return screenSize;
    }

    /**
     * Gets count of tiles on the screen
     * @return the size of screen as tiles
     */
    public Size getScreenSizeAsTiles(){
        return new Size(this.screenSize.getWidth() / this.tileSize.getWidth(),
                this.screenSize.getHeight() / this.tileSize.getHeight());
    }

    public void setScreenSize(Size screenSize) {
        this.screenSize = screenSize;
    }

    @Override
    public void dispose() {
        this.startCoordinates = null;
        this.tileSize = null;
        this.screenSize = null;
        this.mapSize = null;
    }
}

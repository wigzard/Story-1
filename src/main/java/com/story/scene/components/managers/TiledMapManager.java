package com.story.scene.components.managers;

import com.story.scene.components.helpers.MapViewer;
import com.story.scene.components.descriptors.ViewerDescriptor;
import com.story.system.IDisposable;
import com.story.utils.Converter;
import com.story.utils.Size;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;

/**
 * Created by alex on 18.07.16.
 */
public class TiledMapManager implements IDisposable {
    /**
     * Barrier layout on the tiled map
     */
    private static final String BarrierLayerName = "Barrier";

    /**
     * Free layout on the tiled map
     */
    private static final String FreeSpaceLayerName = "FreeSpace";
    /**
     * Property name on the tiled map
     */
    private static final String WidthPropertyName = "width";
    /**
     * Property name on the tiled map
     */
    private static final String HeightPropertyName = "height";
    /**
     * Property name on the tiled map
     */
    private static final String InaccessibleCountPropertyName = "inaccessibleCount";

    /**
     * The tile map
     */
    private TiledMap map;

    /**
     * The viewer of map
     */
    private MapViewer viewer;

    /**
     * The size of screen
     */
    private Size screenSize;

    /**
     * The start position of viewer
     */
    private Point viewerStartPosition;

    public TiledMapManager(TiledMap map, Size screenSize, Point startPosition){
        this.map = map;
        this.screenSize = screenSize;
        this.viewerStartPosition = startPosition;
        this.loadViewer();
    }

    private void loadViewer(){
        int mapWidth = Converter.toInt(this.map.getMapProperty(WidthPropertyName, "0"));
        int mapHeight = Converter.toInt(this.map.getMapProperty(HeightPropertyName, "0"));
        //int inaccessibleTiles = Converter.toInt(this.map.getMapProperty(InaccessibleCountPropertyName, "0"));

        ViewerDescriptor descriptor = new ViewerDescriptor();
        descriptor.setMapSize(new Size(mapWidth, mapHeight));
        descriptor.setScreenSize(this.screenSize);
        descriptor.setStartCoordinates(this.calculateCoordinatesByCentralPoint(this.viewerStartPosition));
        descriptor.setTileSize(new Size(this.map.getTileWidth(), this.map.getTileHeight()));
        this.viewer = new MapViewer(descriptor);
    }

    public TiledMap getMap() {
        return map;
    }

    public void moveRight(){
        this.viewer.moveRight();
    }

    public void moveLeft(){
        this.viewer.moveLeft();
    }

    public void moveUp(){
        this.viewer.moveUp();
    }

    public void moveDown(){
        this.viewer.moveDown();
    }

    /**
     * Call method of gets global coordinates from viewer
     * @return the point of global coordinates
     */
    public Point getCurrentCoordinate(){
        return this.viewer.getGlobalCoordinates();
    }

    /**
     * return global coordinates by central point
     * @param point the point of central object
     * @return global coordinates
     */
    private Point calculateCoordinatesByCentralPoint(Point point){
        int mapWidth = Converter.toInt(this.map.getMapProperty(WidthPropertyName, "0"));
        int mapHeight = Converter.toInt(this.map.getMapProperty(HeightPropertyName, "0"));

        if ((point.x < 0) || (point.x > mapWidth)
                || (point.y < 0) || point.y > mapHeight){
            throw new IllegalArgumentException("The point is outside the map");
        }

        Size tileSize = new Size(this.screenSize.getWidth() / this.map.getTileWidth(),
                this.screenSize.getHeight() / this.map.getTileHeight());
        Point p = new Point(point);
        p.x -= tileSize.getWidth() / 2;
        p.y -= tileSize.getHeight() / 2;

        return p;
    }

    public boolean isVisibleOnViewer(Point p){
        return this.viewer.isVisibleOnViewer(p);
    }

    public boolean isFreeSpace(Point p){
        return this.map.getTileId(p.x, p.y, this.map.getLayerIndex(BarrierLayerName)) == 0;
    }

    /**
     * Gets center tile coordinate
     * @return global point
     */
    public Point getCenterTileAsGlobal(){
        return this.viewer.getViewerCenterPosition();
    }

    /**
     * Gets count of frames which should be draw
     * @return count of frames
     */
    public int getCountFramesInViewer(){
        return this.viewer.getCountFrames();
    }

    @Override
    public void dispose() {
        if (this.viewer != null){
            this.viewer.dispose();
        }

        this.map = null;
        this.viewer = null;
    }
}

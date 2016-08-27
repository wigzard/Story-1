package com.story.scene.components.helpers;

import com.story.system.IDisposable;
import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 21.08.16.
 * Represent common data of components. Uses pattern singleton
 */
public class ComponentCommonHelper implements IDisposable {

    /**
     * The instance of {@link ComponentCommonHelper}
     */
    private static ComponentCommonHelper instance;

    /**
     * Stored size of tiles on map. Should be set from MapComponent.
     */
    private Size tileSize;

    /**
     * Represent map of heights, use when component should understand whe it can be moved to other point.
     * true - point is free
     * false - point isn't free
     */
    private boolean[][] heightsMap;

    /**
     * Initialize new instance of {@link ComponentCommonHelper}
     */
    private ComponentCommonHelper(){}

    /**
     * Get instance of {@link ComponentCommonHelper}
     */
    public static ComponentCommonHelper getInstance(){
        if (instance == null){
            instance = new ComponentCommonHelper();
        }

        return instance;
    }


    public Size getTileSize() {
        return tileSize;
    }

    public void setTileSize(Size tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Sets the map of heights. Default will be initialized
     * in {@link com.story.scene.components.managers.TiledMapManager}
     * @param heightsMap the map of heights
     */
    public void setHeightsMap(boolean[][] heightsMap){
        this.heightsMap = heightsMap;
    }

    /**
     * Check when tile on map is free
     * @param point the point, which should be checked
     * @return true, when object heightsMap can be moved to point
     */
    public boolean isCanBeMoved(Point point) {
        return !(this.heightsMap == null
                || this.heightsMap.length < point.x
                || this.heightsMap[point.x].length < point.y) && this.heightsMap[point.x][point.y];

    }

    /**
     * Moved actor to new point if it is can be executed. Point "from" releases for other actors
     * @param from the point from whence the actor moved
     * @param to the point where actor trying to moved
     */
    public synchronized void moveActor(Point from, Point to){
        if (!this.isCanBeMoved(to)){
            return;
        }

        this.heightsMap[from.x][from.y] = true;
        this.heightsMap[to.x][to.y] = false;
    }

    /**
     * Sets the actor to "point" point if it can be executed
     * @param point the coordinate of actor
     */
    public synchronized void setActor(Point point){
        if (!this.isCanBeMoved(point)){
            return;
        }

        this.heightsMap[point.x][point.y] = false;
    }

    @Override
    public void dispose() {
        this.tileSize = null;
        this.heightsMap = null;
    }
}

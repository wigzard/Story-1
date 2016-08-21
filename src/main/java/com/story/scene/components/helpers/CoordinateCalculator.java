package com.story.scene.components.helpers;

import com.story.application.ApplicationSettings;
import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 14.08.16.
 * Represent methods of calculate coordinates on map
 */
public class CoordinateCalculator {
    private CoordinateCalculator(){}

    /**
     * Convert point with tile coordinates to point with global coordinates
     * @param tilePoint the tile point on map
     * @param tileSize the size of tile
     * @return global coordinates of point
     */
    public static Point convertToGlobal(Point tilePoint, Size tileSize){
        if ((tilePoint == null) || (tileSize == null)){
            throw new IllegalArgumentException("Invalid incoming arguments");
        }

        return new Point(tilePoint.x * tileSize.getWidth(), tilePoint.y * tileSize.getHeight());
    }

    /**
     * Check when object is visible on viewer by tile coordinates
     * @param tilePoint the tile point of object
     * @param tileSize the size of tile
     * @param globalStartViewerPoint the global point of viewer
     * @return true, when object is visible on viewer
     */
    public static boolean isVisibleOnViewer(Point tilePoint, Size tileSize, Point globalStartViewerPoint){
        return isVisibleOnViewer(convertToGlobal(tilePoint, tileSize), globalStartViewerPoint);
    }

    /**
     * Check when object is visible on viewer by global
     * @param globalPoint the global point of object
     * @param globalStartViewerPoint the global point of viewer
     * @return true, when object is visible on viewer
     */
    public static boolean isVisibleOnViewer(Point globalPoint, Point globalStartViewerPoint){
        if ((globalPoint == null) || (globalStartViewerPoint == null)){
            throw new IllegalArgumentException("Invalid incoming arguments");
        }

        Point globalEndViewerPoint = new Point(
                globalStartViewerPoint.x + ApplicationSettings.getSettings().getScreenWidth(),
                globalStartViewerPoint.y + ApplicationSettings.getSettings().getScreenHeight());

        return globalPoint.x >= globalStartViewerPoint.x
                && globalPoint.x <= globalEndViewerPoint.x
                && globalPoint.y >= globalStartViewerPoint.y
                && globalPoint.y <= globalEndViewerPoint.y;
    }
}

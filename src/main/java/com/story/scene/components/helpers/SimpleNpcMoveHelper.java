package com.story.scene.components.helpers;

import com.story.system.IDisposable;
import com.story.utils.Size;

import java.awt.*;
import java.util.*;

/**
 * Created by alex on 20.08.16.
 * Represent handler for moving of npc
 */
public class SimpleNpcMoveHelper implements IDisposable {
    /**
     * Count steps of npc, when he is moved to next tile
     */
    private static final int countOfSteps = 5;

    /**
     * The size of tiles on map
     */
    private Size tileSize;

    /**
     * Frames of move for npc
     */
    private Queue<MovementHelper> frames;

    /**
     * Initialize the instance of {@link SimpleNpcMoveHelper}
     * @param tileSize the size of tile on map
     */
    public SimpleNpcMoveHelper(Size tileSize){
        if (tileSize == null){
            throw new NullPointerException("Size of tile shouldn't be null");
        }

        this.tileSize = tileSize;
    }

    /**
     * Creates the steps queue for move of npc
     * @param startPoint the point of start position of npc
     * @param endPoint the point of end position of npc
     */
    public void createSteps(Point startPoint, Point endPoint){
        if ((startPoint == null) || (endPoint == null)){
            throw new NullPointerException("Point of start or end shouldn't be null");
        }

        if (startPoint.equals(endPoint)){
            return;
        }

        if (this.frames == null){
            this.frames = new LinkedList<>();
        }

        if (this.frames.size() > 0){
            return;
        }

        for (int i = 0; i < countOfSteps; i++){
            MovementHelper mh = new MovementHelper();
            mh.startPoint = startPoint;
            mh.endPoint = endPoint;
            mh.stepNumber = i + 1;
            this.frames.add(mh);
        }
    }

    /**
     * Calculate next step of npc
     * @param globalViewerStartPoint the global point of the viewer
     * @return the point for render of npc
     */
    public Point getNextPoint(Point globalViewerStartPoint){
        if ((this.frames == null) || (this.frames.size() == 0)){
            return null;
        }

        if (globalViewerStartPoint == null){
            throw new NullPointerException("Start point of viewer shouldn't be null");
        }

        MovementHelper helper = this.frames.poll();

        Point nextPoint = new Point();
        nextPoint.x = helper.startPoint.x + helper.stepNumber * this.tileSize.getWidth();
        nextPoint.y = helper.startPoint.y + helper.stepNumber * this.tileSize.getHeight();

        nextPoint.x = (nextPoint.x > helper.endPoint.x)? helper.endPoint.x : nextPoint.x;
        nextPoint.y = (nextPoint.y > helper.endPoint.y)? helper.endPoint.y : nextPoint.y;

        return new Point(nextPoint.x - globalViewerStartPoint.x, nextPoint.y - globalViewerStartPoint.y);
    }

    @Override
    public void dispose() {
        this.tileSize = null;
        this.frames = null;
    }

    /**
     * Represent descriptor of move
     */
    private class MovementHelper{
        /**
         * Start point before move
         */
        private Point startPoint;

        /**
         * End point after move
         */
        private Point endPoint;

        /**
         * The number of step
         */
        private int stepNumber;
    }
}

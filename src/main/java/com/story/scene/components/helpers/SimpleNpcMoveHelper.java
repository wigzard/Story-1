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
    private static final int countOfSteps = 10;

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
    public synchronized void createSteps(Point startPoint, Point endPoint){
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
    public synchronized Point getNextPoint(Point globalViewerStartPoint){
        if (globalViewerStartPoint == null){
            throw new NullPointerException("Start point of viewer shouldn't be null");
        }

        if ((this.frames == null) || (this.frames.size() == 0)){
            return null;
        }

        MovementHelper helper = this.frames.poll();

        Point nextPoint = new Point();
        nextPoint.x = helper.endPoint.x * ComponentCommonHelper.getInstance().getTileSize().getWidth();
        nextPoint.y = helper.endPoint.y * ComponentCommonHelper.getInstance().getTileSize().getHeight();
        if (helper.startPoint.x != helper.endPoint.x) {
            int direction = helper.startPoint.x > helper.endPoint.x? -1 : 1;
            nextPoint.x = helper.startPoint.x
                    * this.tileSize.getWidth()
                    + helper.stepNumber
                    * this.tileSize.getWidth()
                    / countOfSteps
                    * direction;
        }

        if (helper.startPoint.y != helper.endPoint.y) {
            int direction = helper.startPoint.y > helper.endPoint.y? -1 : 1;
            nextPoint.y = helper.startPoint.y
                    * this.tileSize.getHeight()
                    + helper.stepNumber
                    * this.tileSize.getHeight()
                    / countOfSteps
                    * direction;
        }

        return nextPoint;
    }

    /**
     * Check the count of frames in queues
     * @return true, when queue is empty
     */
    public boolean isFramesEmpty(){
        return this.frames == null || this.frames.isEmpty();
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

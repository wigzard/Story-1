package com.story.scene.components.managers;

import com.story.scene.components.descriptors.SimpleNpcDescriptor;
import com.story.scene.components.helpers.*;
import com.story.system.IDisposable;
import com.story.utils.asyncTask.TaskScheduler;

import java.awt.*;

/**
 * Created by alex on 06.08.16.
 * Represent the manager which handling all operation with instance of
 * {@link com.story.scene.components.SimpleNpcComponent}
 */
public class SimpleNpcComponentManager implements IDisposable {
    private static final int NpcAnimationDuration = 300;

    /**
     * The animation of npc
     */
    private ActorAnimationHelper npcAnimation;

    /**
     * The descriptor of simple npc component
     */
    private SimpleNpcDescriptor descriptor;

    /**
     * The position of npc, where he is staying in every moment of time
     */
    private Point currentCoordinate;

    /**
     * The position of npc, where he is staying in every moment of time
     */
    private Point currentGlobalCoordinate;

    /**
     * The helper which formed steps of the npc actor
     */
    private SimpleNpcMoveHelper moveHelper;

    /**
     * The direction of actor.Default value is DOWN
     */
    private ActorDirection actorDirection = ActorDirection.DOWN;

    /**
     * Initialize new instance of {@link SimpleNpcComponentManager}
     * @param descriptor the descriptor of npc
     */
    public SimpleNpcComponentManager(SimpleNpcDescriptor descriptor){
        if (descriptor == null){
            throw new NullPointerException("The SimpleNpcDescriptor shouldn't be null");
        }

        this.descriptor = descriptor;
        this.currentCoordinate = this.descriptor.getStartPosition();
        ComponentCommonHelper.getInstance().setActor(this.currentCoordinate);
        this.npcAnimation = new ActorAnimationHelper(this.descriptor.getSpriteSheetPath(),
                ComponentCommonHelper.getInstance().getTileSize(),
                NpcAnimationDuration);
        this.npcAnimation.setAutoUpdate(true);

        this.initializeMovementBehavior();
    }

    /**
     * Create async task and run it. Describe behavior of movement
     */
    private void initializeMovementBehavior(){
        this.moveHelper = new SimpleNpcMoveHelper(ComponentCommonHelper.getInstance().getTileSize());

        TaskScheduler.scheduleCycleTask("npc" + this.descriptor.getId(), () -> {
            if (!this.moveHelper.isFramesEmpty()){
                return;
            }

            Point startPoint = new Point(this.currentCoordinate);
            Point endPoint = new Point(this.currentCoordinate);

            this.actorDirection = ActorDirection.random();
            switch (this.actorDirection){
                case DOWN:
                    endPoint.y++;
                    break;
                case UP:
                    endPoint.y--;
                    break;
                case LEFT:
                    endPoint.x--;
                    break;
                case RIGHT:
                    endPoint.x++;
                    break;
            }

            if (ComponentCommonHelper.getInstance().isCanBeMoved(endPoint)) {
                this.moveHelper.createSteps(startPoint, endPoint);
                this.currentCoordinate = endPoint;
                ComponentCommonHelper.getInstance().moveActor(startPoint, endPoint);
            }
        }, this.descriptor.getMoveInterval());
    }

    /**
     * Update the npc animation
     * @param delta the delta value
     */
    public void updateNpc(int delta){
        this.npcAnimation.update(ActorDirection.DOWN, delta);
    }

    /**
     * Draw animation of actor component
     */
    public void drawNpc(){
        if (this.currentGlobalCoordinate == null){
            return;
        }

        this.npcAnimation.draw(this.actorDirection, this.currentGlobalCoordinate.x, this.currentGlobalCoordinate.y);
    }

    /**
     * Calculate the global position of npc
     * @param globalViewerStartPoint the start point of viewer
     */
    public void updateGlobalPosition(Point globalViewerStartPoint){
        Point renderPoint;
        if (this.moveHelper.isFramesEmpty()) {
            renderPoint = new Point(
                    this.currentCoordinate.x * ComponentCommonHelper.getInstance().getTileSize().getWidth(),
                    this.currentCoordinate.y * ComponentCommonHelper.getInstance().getTileSize().getHeight());
        }
        else {
            renderPoint = this.moveHelper.getNextPoint(globalViewerStartPoint);
        }

        //Area of the viewer should be increased, because on border of the viewer,
        //elements becomes invisible
        Point extendGlobalViewerStartPoint = new Point(
                globalViewerStartPoint.x - ComponentCommonHelper.getInstance().getTileSize().getWidth(),
                globalViewerStartPoint.y - ComponentCommonHelper.getInstance().getTileSize().getHeight());

        if (!CoordinateCalculator.isVisibleOnViewer(renderPoint, extendGlobalViewerStartPoint)){
            this.currentGlobalCoordinate = null;
            return;
        }

        this.currentGlobalCoordinate = new Point(renderPoint.x - globalViewerStartPoint.x, renderPoint.y - globalViewerStartPoint.y);
    }

    @Override
    public void dispose() {
        TaskScheduler.terminate("npc" + this.descriptor.getId());

        if (this.descriptor != null){
            this.descriptor.dispose();
        }

        if (this.moveHelper != null){
            this.moveHelper.dispose();
        }

        this.moveHelper = null;
        this.descriptor = null;
    }
}

package com.story.scene.components.managers;

import com.story.scene.components.MapComponent;
import com.story.scene.components.descriptors.SimpleNpcDescriptor;
import com.story.scene.components.helpers.ActorAnimationHelper;
import com.story.scene.components.helpers.ActorDirection;
import com.story.scene.components.helpers.ComponentCommonVariable;
import com.story.scene.components.helpers.CoordinateCalculator;
import com.story.system.IDisposable;

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
     * Initialize new instance of {@link SimpleNpcComponentManager}
     * @param descriptor the descriptor of npc
     */
    public SimpleNpcComponentManager(SimpleNpcDescriptor descriptor){
        if (descriptor == null){
            throw new NullPointerException("The SimpleNpcDescriptor shouldn't be null");
        }

        this.descriptor = descriptor;
        this.currentCoordinate = this.descriptor.getStartPosition();
        this.npcAnimation = new ActorAnimationHelper(this.descriptor.getSpriteSheetPath(),
                ComponentCommonVariable.getInstance().getTileSize(),
                NpcAnimationDuration);
        this.npcAnimation.setAutoUpdate(true);
    }

    /**
     * Update the npc animation
     * @param delta the delta value
     */
    public void updateNpc(int delta){
        this.npcAnimation.update(ActorDirection.DOWN, delta);
    }

    /**
     * Draw player component animation
     */
    public void drawNpc(){
        if (this.currentGlobalCoordinate == null){
            return;
        }

        this.npcAnimation.draw(ActorDirection.DOWN, this.currentGlobalCoordinate.x, this.currentGlobalCoordinate.y);
    }

    /**
     * Calculate the global position of npc
     * @param globalViewerStartPoint the start point of viewer
     */
    public void calculateGlobalPosition(Point globalViewerStartPoint){
        Point renderPoint = new Point(
                this.currentCoordinate.x * ComponentCommonVariable.getInstance().getTileSize().getWidth(),
                this.currentCoordinate.y * ComponentCommonVariable.getInstance().getTileSize().getHeight());

        //Area of the viewer should be increased, because on border of the viewer,
        //elements becomes invisible
        Point extendGlobalViewerStartPoint = new Point(
                globalViewerStartPoint.x - ComponentCommonVariable.getInstance().getTileSize().getWidth(),
                globalViewerStartPoint.y - ComponentCommonVariable.getInstance().getTileSize().getHeight());

        if (!CoordinateCalculator.isVisibleOnViewer(renderPoint, extendGlobalViewerStartPoint)){
            this.currentGlobalCoordinate = null;
            return;
        }

        this.currentGlobalCoordinate = new Point(renderPoint.x - globalViewerStartPoint.x, renderPoint.y - globalViewerStartPoint.y);
    }

    @Override
    public void dispose() {
        if (this.descriptor != null){
            this.descriptor.dispose();
        }

        this.descriptor = null;
    }
}

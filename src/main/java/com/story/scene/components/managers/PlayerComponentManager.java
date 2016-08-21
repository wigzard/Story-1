package com.story.scene.components.managers;

import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.scene.components.helpers.ActorAnimationHelper;
import com.story.scene.components.helpers.ActorDirection;
import com.story.scene.components.helpers.ComponentCommonVariable;
import com.story.system.IDisposable;

import java.awt.*;

/**
 * Created by alex on 06.08.16.
 * Represent class for manage a component of player
 */
public class PlayerComponentManager implements IDisposable {
    private static final int PlayerAnimationDuration = 200;

    /**
     * The animation of player
     */
    private ActorAnimationHelper playerAnimation;

    /**
     * The position of player, where he stay in every time
     */
    private Point currentCoordinate;

    /**
     * The descriptor of player
     */
    private PlayerDescriptor playerDescriptor;

    /**
     * The current direction of actor
     */
    private ActorDirection currentDirection;

    /**
     * Initialize new instance of {@link PlayerComponentManager}
     * @param descriptor the descriptor of player
     */
    public PlayerComponentManager(PlayerDescriptor descriptor){
        if (descriptor == null){
            throw  new NullPointerException("Player descriptor shouldn't be null");
        }

        this.playerDescriptor = descriptor;
        this.currentCoordinate = this.playerDescriptor.getStartPosition();
        this.currentDirection = ActorDirection.DOWN;

        this.playerAnimation = new ActorAnimationHelper(this.playerDescriptor.getSpriteSheetPath(),
                ComponentCommonVariable.getInstance().getTileSize(),
                PlayerAnimationDuration);
    }

    /**
     * Move player to other point
     * @param p the point with new coordinates
     */
    public void moveTo(Point p){
        if ((p == null) || (this.currentCoordinate.equals(p))){
            return;
        }

        this.currentCoordinate = p;
    }

    /**
     * Sets direction of player
     * @param direction selected direction
     */
    public void setDirection(ActorDirection direction){
        this.currentDirection = direction;
    }

    public ActorDirection getDirection(){
        return this.currentDirection;
    }

    /**
     * Update the player animation
     * @param delta the delta value
     */
    public void updatePlayerAnimation(int delta){
        this.playerAnimation.update(this.currentDirection, delta);
    }

    /**
     * Draw player component animation
     */
    public void drawPlayerAnimation(){
        Point renderPoint = new Point(this.playerDescriptor.getCenterPosition().x,
                this.playerDescriptor.getCenterPosition().y);

        this.playerAnimation.draw(this.currentDirection, renderPoint.x, renderPoint.y);
    }

    /**
     * Gets current point of player
     * @return instance of {@link Point}
     */
    public Point getCurrentPoint(){
        return new Point(this.currentCoordinate);
    }

    /**
     * Change auto update status for player
     */
    public void changeAutoUpdate(boolean value){
       this.playerAnimation.setAutoUpdate(value);
    }

    @Override
    public void dispose() {
        if (this.playerDescriptor != null){
            this.playerDescriptor.dispose();
        }

        if (this.playerAnimation != null){
            this.playerAnimation.dispose();
        }

        this.playerDescriptor = null;
        this.playerAnimation = null;
        this.currentCoordinate = null;
    }
}

package com.story.scene.components;

import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.scene.components.helpers.ActorDirection;
import com.story.scene.components.managers.PlayerComponentManager;
import com.story.utils.customException.InvalidDescriptor;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 23.07.16.
 * Represent the player component
 */
public class PlayerComponent extends ActorComponent {
    public static final String PlayerMoveStopPropertyName = "PlayerStop";
    public static final String PlayerMoveStartPropertyName = "PlayerStart";

    /**
     * The manager of player component
     */
    private PlayerComponentManager componentManager;

    /**
     * Initialize new instance of PlayerComponent
     * @param descriptor the descriptor of player
     */
    public PlayerComponent(PlayerDescriptor descriptor){
        this.componentManager = new PlayerComponentManager(descriptor);
    }

    /**
     * Move player to other point
     * @param p the point with new coordinates
     */
    public void moveTo(Point p){
        this.componentManager.moveTo(p);
    }

    /**
     * Sets direction of player
     * @param direction selected direction
     */
    public void setDirection(ActorDirection direction){
        this.componentManager.setDirection(direction);
    }

    public ActorDirection getDirection(){
        return this.componentManager.getDirection();
    }

    public Point getCurrentCoordinate() {
        return this.componentManager.getCurrentPoint();
    }

    /**
     * Stopped the player animation
     */
    public Void onStopMoveAnimation(Void v){
        this.componentManager.changeAutoUpdate(false);
        return null;
    }

    /**
     * Started the player animation
     */
    public Void onStartMoveAnimation(Void v){
        this.componentManager.changeAutoUpdate(true);
        return null;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException, InvalidDescriptor {
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.componentManager.updatePlayerAnimation(delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.componentManager.drawPlayerAnimation();
    }

    @Override
    public void dispose(){
        super.dispose();

        if (this.componentManager != null){
            this.componentManager.dispose();
        }

        this.componentManager = null;
    }
}

package com.story.scene.components;

import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.scene.components.managers.PlayerComponentManager;
import com.story.utils.customException.InvalidDescriptor;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created by alex on 23.07.16.
 * Represent the player component
 */
public class PlayerComponent extends ActorComponent {
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

    public Point getCurrentCoordinate() {
        return this.componentManager.getCurrentPoint();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException, InvalidDescriptor {
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.componentManager.getPlayerAnimation().update(delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.componentManager.getPlayerAnimation().draw(this.componentManager.getRenderPoint().x,
                this.componentManager.getRenderPoint().y);
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}

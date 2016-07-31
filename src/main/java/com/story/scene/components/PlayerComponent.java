package com.story.scene.components;

import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.utils.customException.InvalidDescriptor;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

import java.awt.*;

/**
 * Created by alex on 23.07.16.
 * Represent the player component
 */
public class PlayerComponent extends ActorComponent {
    private Animation playerAnimation;
    private PlayerDescriptor descriptor;
    private Point currentCoordinate;

    /**
     * Initialize new instance of PlayerComponent
     * @param descriptor the descriptor of player
     */
    public PlayerComponent(PlayerDescriptor descriptor){
        this.descriptor = descriptor;
        this.currentCoordinate = this.descriptor.getStartPosition();
    }

    /**
     * Move player to other point
     * @param p the point with new coordinates
     */
    public void moveTo(Point p){
        if (this.currentCoordinate.equals(p)){
            return;
        }

        this.currentCoordinate = p;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException, InvalidDescriptor {
        this.playerAnimation = new Animation(new SpriteSheet(
                this.descriptor.getUrl(),
                this.descriptor.getTileSize().getWidth(),
                this.descriptor.getTileSize().getHeight()), 250);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.playerAnimation.update(delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.playerAnimation.draw(this.descriptor.getCenterPosition().x,
                this.descriptor.getCenterPosition().y);
    }

    @Override
    public void dispose(){
        super.dispose();

        this.playerAnimation = null;
    }

    public Point getCurrentCoordinate() {
        return currentCoordinate;
    }
}

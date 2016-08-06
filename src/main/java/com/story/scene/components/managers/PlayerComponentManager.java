package com.story.scene.components.managers;

import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.system.IDisposable;
import com.story.utils.GlobalHelper;
import com.story.utils.Size;
import com.story.utils.log.Trace;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.awt.*;
import java.io.FileNotFoundException;

/**
 * Created by alex on 06.08.16.
 * Represent class for manage a component of player
 */
public class PlayerComponentManager implements IDisposable {
    private static final int PlayerAnimationDuration = 250;

    /**
     * The animation of player
     */
    private Animation playerAnimation;

    /**
     * The position of player, where he stay in every time
     */
    private Point currentCoordinate;

    /**
     * The descriptor of player
     */
    private PlayerDescriptor playerDescriptor;

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

    /**
     * Gets the animation of player
     * @return instance of {@link Animation}
     */
    public Animation getPlayerAnimation(){
        if (this.playerAnimation == null){
            try {
                this.playerAnimation = this.createAnimation(this.playerDescriptor.getSpriteSheetPath(),
                        this.playerDescriptor.getTileSize(),
                        PlayerAnimationDuration);
            } catch (FileNotFoundException | SlickException e) {
                Trace.error(e);
                return null;
            }
        }

        return this.playerAnimation;
    }

    /**
     * Gets current point of player
     * @return instance of {@link Point}
     */
    public Point getCurrentPoint(){
        return new Point(this.currentCoordinate);
    }

    /**
     * Gets current position of player as global coordinates
     * @return instance of {@link Point}
     */
    public Point getRenderPoint(){
        return new Point(this.playerDescriptor.getCenterPosition().x,
                this.playerDescriptor.getCenterPosition().y);
    }

    /**
     * Creates the animation for player
     * @param pathToSpriteSheet the path to file with sprites
     * @param tileSize the size of tile
     * @param duration the duration of animation
     * @return instance of animation
     */
    private Animation createAnimation(String pathToSpriteSheet, Size tileSize, int duration) throws FileNotFoundException, SlickException {
        if (!GlobalHelper.isFileExists(pathToSpriteSheet)){
            throw  new FileNotFoundException("File " + pathToSpriteSheet + " doesn't exists");
        }

        return new Animation(new SpriteSheet(
                pathToSpriteSheet,
                tileSize.getWidth(),
                tileSize.getHeight()), duration);
    }

    @Override
    public void dispose() {
        if (this.playerDescriptor != null){
            this.playerDescriptor.dispose();
        }

        this.playerDescriptor = null;
        this.playerAnimation = null;
        this.currentCoordinate = null;
    }
}

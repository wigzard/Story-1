package com.story.scene.components.helpers;

import com.story.system.IDisposable;
import com.story.utils.GlobalHelper;
import com.story.utils.Size;
import com.story.utils.log.Trace;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by alex on 06.08.16.
 * Represent class which can parse the SpriteSheet of actor and manipulate they by situation.
 */
public class ActorAnimationHelper implements IDisposable {
    private static final int DefaultActorImage = 0;

    /**
     * Variable have true value when animation should be enabled
     */
    private boolean isAutoUpdate;

    /**
     * The animations by direction
     */
    private HashMap<ActorDirection, Animation> actorAnimationsByDirection;

    /**
     * Initialize new instance of {@link ActorAnimationHelper}
     * @param pathToSpriteSheet path to sprite sheet
     * @param tileSize the size of tile
     * @param duration the duration of animation
     */
    public ActorAnimationHelper(String pathToSpriteSheet, Size tileSize, int duration){
        this.actorAnimationsByDirection = this.buildDirectionAnimation(pathToSpriteSheet, tileSize, duration);
        this.isAutoUpdate = false;
    }

    /**
     * Building the animation by direction
     * @param pathToSpriteSheet path to sprite sheet
     * @param tileSize the size of tile
     * @param duration the duration of animation
     * @return animation sorted by direction
     */
    private HashMap<ActorDirection, Animation> buildDirectionAnimation(String pathToSpriteSheet,
                                                                       Size tileSize,
                                                                       int duration) {
        try {
            if (!GlobalHelper.isFileExists(pathToSpriteSheet)) {
                throw new FileNotFoundException("File " + pathToSpriteSheet + " doesn't exists");
            }

            SpriteSheet spriteSheet = new SpriteSheet(pathToSpriteSheet, tileSize.getWidth(), tileSize.getHeight());

            Image[] downImages = this.getSubImagesByHorizontal(spriteSheet, 0);
            Image[] leftImages = this.getSubImagesByHorizontal(spriteSheet, 1);
            Image[] rightImages = this.getSubImagesByHorizontal(spriteSheet, 2);
            Image[] upImages = this.getSubImagesByHorizontal(spriteSheet, 3);

            this.actorAnimationsByDirection = new HashMap<>();

            if (downImages != null) {
                this.actorAnimationsByDirection.put(ActorDirection.DOWN, new Animation(downImages, duration));
                //this.actorAnimationsByDirection.get(ActorDirection.DOWN).setAutoUpdate(true);
            }

            if (leftImages != null) {
                this.actorAnimationsByDirection.put(ActorDirection.LEFT, new Animation(leftImages, duration));
            }

            if (rightImages != null) {
                this.actorAnimationsByDirection.put(ActorDirection.RIGHT, new Animation(rightImages, duration));
            }

            if (upImages != null) {
                this.actorAnimationsByDirection.put(ActorDirection.UP, new Animation(upImages, duration));
            }

            return this.actorAnimationsByDirection;
        }
        catch (Exception e){
            Trace.error(e);
            return null;
        }
    }

    /**
     * Gets sub images from big sprite
     * @param sprite the common sprite
     * @param lineNumber number of line
     * @return array of images
     */
    private Image[] getSubImagesByHorizontal(SpriteSheet sprite, int lineNumber){
        if (sprite == null){
            throw new NullPointerException("SpriteSheet shouldn't be null");
        }

        if (sprite.getVerticalCount() < lineNumber){
            return null;
        }

        Image[] images = new Image[sprite.getHorizontalCount()];
        for (int i = 0; i < sprite.getHorizontalCount(); i++){
            images[i] = sprite.getSubImage(i, lineNumber);
        }

        return images;
    }

    /**
     * Change value for isAutoUpdate
     * @param value the new value
     */
    public void setAutoUpdate(boolean value){
        this.isAutoUpdate = value;
    }

    /**
     * Updating animation by direction
     * @param direction the direction of actor
     * @param delta the delta
     */
    public void update(ActorDirection direction, int delta){
        if ((this.actorAnimationsByDirection == null) || !(this.actorAnimationsByDirection.containsKey(direction))){
            return;
        }

        if (this.isAutoUpdate){
            this.actorAnimationsByDirection.get(direction).update(delta);
        }
    }

    /**
     * Draw the animation
     * @param direction the direction of actor
     * @param x draw position by vertical
     * @param y draw position by horizontal
     */
    public void draw(ActorDirection direction, int x, int y){
        if ((this.actorAnimationsByDirection == null) || !(this.actorAnimationsByDirection.containsKey(direction))){
            return;
        }

        if (this.isAutoUpdate){
            this.actorAnimationsByDirection.get(direction).draw(x, y);
        }
        else {
            this.actorAnimationsByDirection.get(direction).getImage(DefaultActorImage).draw(x, y);
        }
    }

    @Override
    public void dispose() {
        this.actorAnimationsByDirection = null;
    }
}

package com.story.game.handlers;

import com.story.core.baseHandlers.PlayerHandler;
import com.story.game.factories.AnimationFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.HashMap;

/**
 * Created by alex on 16.04.16.
 */
public class BasePlayerHandler extends PlayerHandler {
    public BasePlayerHandler(PersonDescriptor person) throws SlickException {
        super(person);
    }

    @Override
    public void move(Direction d) {
        switch (d){
            case DOWN:
                this.currentPosition.y++;
                break;
            case UP:
                this.currentPosition.y--;
                break;
            case RIGHT:
                this.currentPosition.x++;
                break;
            case LEFT:
                this.currentPosition.x--;
                break;
        }
    }

    @Override
    public void init() throws SlickException {
        //Image i = new Image(this.playerDescriptor.getPathPersonPictureSet());

        /*Animation left = new Animation(false);
        left.addFrame(i.getSubImage(32, 32, 32, 32), 100);
        left.addFrame(i.getSubImage(0, 32, 32, 32), 100);
        left.addFrame(i.getSubImage(64, 32, 32, 32), 100);

        Animation up = new Animation(false);
        up.addFrame(i.getSubImage(32, 96, 32, 32), 100);
        up.addFrame(i.getSubImage(0, 96, 32, 32), 100);
        up.addFrame(i.getSubImage(64, 96, 32, 32), 100);

        Animation right = new Animation(false);
        right.addFrame(i.getSubImage(32, 64, 32, 32), 100);
        right.addFrame(i.getSubImage(0, 64, 32, 32), 100);
        right.addFrame(i.getSubImage(64, 64, 32, 32), 100);

        this.moveAnimation = new HashMap<>();
        this.moveAnimation.put(Direction.LEFT, left);
        this.moveAnimation.put(Direction.RIGHT, right);
        this.moveAnimation.put(Direction.UP, up);*/

        this.moveAnimation = new HashMap<>();

        this.moveAnimation.put(Direction.DOWN,
                AnimationFactory.createPlayerAnimation(Direction.DOWN, this.playerDescriptor));
        this.moveAnimation.put(Direction.LEFT,
                AnimationFactory.createPlayerAnimation(Direction.LEFT, this.playerDescriptor));
        this.moveAnimation.put(Direction.RIGHT,
                AnimationFactory.createPlayerAnimation(Direction.RIGHT, this.playerDescriptor));
        this.moveAnimation.put(Direction.UP,
                AnimationFactory.createPlayerAnimation(Direction.UP, this.playerDescriptor));
    }

    @Override
    public void setCurrentDirection(Direction d) {
        this.curreDirection = d;
    }
}

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

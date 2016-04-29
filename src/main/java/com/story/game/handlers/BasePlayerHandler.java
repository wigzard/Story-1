package com.story.game.handlers;

import com.story.core.baseHandlers.PlayerHandler;
import com.story.core.frames.CentralObject;
import com.story.modules.dbdata.view.PersonDescriptor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

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
        //this.playerSheet = new SpriteSheet(this.playerData.getPathPersonPictureSet(), 32, 32);
        //this.moveAnimation = new Animation(this.playerSheet, 100);

        Image i = new Image(this.playerData.getPathPersonPictureSet());
        this.moveAnimation = new Animation(false);

        this.moveAnimation.addFrame(i.getSubImage(0, 0, 32, 32), 100);
        this.moveAnimation.addFrame(i.getSubImage(32, 0, 32, 32), 100);
        this.moveAnimation.addFrame(i.getSubImage(64, 0, 32, 32), 100);
    }
}

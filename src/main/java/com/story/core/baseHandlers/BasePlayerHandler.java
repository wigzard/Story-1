package com.story.core.baseHandlers;

import com.story.modules.dbdata.view.PersonData;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 16.04.16.
 */
public class BasePlayerHandler extends PlayerHandler {
    public BasePlayerHandler(PersonData person) {
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

    }
}

package com.story.core.entities;

import com.story.game.action.MoveAction;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 18.05.16.
 */
public abstract class Npc extends PersonEntity {
    private MoveAction moveAction;

    public Npc(PersonDescriptor person, Point startPosition) throws SlickException {
        super(person, startPosition);
        this.moveAction = new MoveAction(this.descriptor.getName());
    }
}

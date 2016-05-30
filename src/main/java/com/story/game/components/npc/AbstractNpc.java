package com.story.game.components.npc;

import com.story.core.entities.Npc;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 29.05.16.
 */
public abstract class AbstractNpc extends Npc {
    public AbstractNpc(PersonDescriptor person, Point startPosition) throws SlickException {
        super(person, startPosition);
    }

    public abstract void update();
}

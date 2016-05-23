package com.story.core.entities;

import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public abstract class Player extends PersonEntity {
    public Player(PersonDescriptor person) throws SlickException {
        super(person, new Point(0, 0));
    }
}

package com.story.core.entities;

import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 18.05.16.
 */
public abstract class Npc extends PersonEntity {
    public Npc(PersonDescriptor person, Point startPosition) throws SlickException {
        super(person, startPosition);
    }

    /**
     * Get the name of npc
     * @return the name
     */
    public String getName(){
        if (this.descriptor == null){
            return null;
        }

        return this.descriptor.getName();
    }

    /**
     * @return the Npc id
     */
    public int getId(){
        if (this.descriptor == null){
            return -1;
        }

        return this.descriptor.getId();
    }
}

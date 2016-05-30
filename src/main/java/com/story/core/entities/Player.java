package com.story.core.entities;

import com.story.core.actions.Callback;
import com.story.core.actions.CallbackArgs;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.global.ActionType;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by alex on 09.04.16.
 */
public abstract class Player extends PersonEntity {
    /**
     * The list of callback. When handlers is occurs, call the callback.
     */
    protected HashMap<ActionType, Callback<Boolean, CallbackArgs>> callbackList;

    public Player(PersonDescriptor person) throws SlickException {
        super(person, new Point(0, 0));
    }

    /**
     * Added a callback to list of the event listener
     * @param type the type of handlers/event
     * @param callback a callback what call on an handlers
     */
    public void addEventListener(ActionType type, Callback<Boolean, CallbackArgs> callback){
        if (callback == null){
            return;
        }

        if (this.callbackList == null){
            this.callbackList = new HashMap<>();
        }

        this.callbackList.put(type, callback);
    }

    /**
     * Remove a callback from list of a callback
     * @param type the type of handlers/event
     */
    public void removeEventListener(ActionType type){
        if ((this.callbackList == null) || (this.callbackList.size() == 0)){
            return;
        }

        if (this.callbackList.containsKey(type)){
            this.callbackList.remove(type);
        }
    }
}

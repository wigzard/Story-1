package com.story.core.baseHandlers;

import org.newdawn.slick.Input;

import java.util.HashMap;

/**
 * Created by alex on 16.04.16.
 */
public class KeyEventHandler {
    private HashMap<Integer, Callback> keysEvent;

    public KeyEventHandler(){
        this.keysEvent = new HashMap<>();
    }

    public void registerCallback(int keyCode, Callback callback){
        this.keysEvent.put(keyCode, callback);
    }

    public void run(Input input){
        if (this.keysEvent.size() == 0){
            return;
        }

        for (Integer key : this.keysEvent.keySet()){
            if (input.isKeyPressed(key)){
                this.keysEvent.get(key).execute();
                return;
            }
        }
    }

    public interface Callback{
        void execute();
    }
}

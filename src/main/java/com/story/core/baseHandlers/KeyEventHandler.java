package com.story.core.baseHandlers;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

import java.util.HashMap;

/**
 * Created by alex on 16.04.16.
 */
public class KeyEventHandler{
    private HashMap<Integer, KeyHandler> keysEventMap;

    public KeyEventHandler(){
        this.keysEventMap = new HashMap<>();
    }

    public void addHandler(int keyCode, KeyHandler handler){
        this.keysEventMap.put(keyCode, handler);
    }

    public void run(Input input){
        if (this.keysEventMap.size() == 0){
            return;
        }

        for (Integer key : this.keysEventMap.keySet()){
            if (input.isKeyDown(key)){
                this.keysEventMap.get(key).execute();
                //return;
            }
        }
    }

    public interface KeyHandler{
        void execute();
    }
}

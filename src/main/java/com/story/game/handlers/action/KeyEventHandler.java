package com.story.game.handlers.action;

import com.story.core.coreHandlers.EventArgs;
import com.story.core.coreHandlers.IEventHandler;
import com.story.game.eventArgs.KeyEventArgs;
import com.story.game.storages.ProxyScope;
import org.newdawn.slick.Input;

import java.util.HashMap;

/**
 * Created by alex on 16.04.16.
 */
public class KeyEventHandler implements IEventHandler{
    private HashMap<Integer, KeyHandler> keysEventMap;

    public KeyEventHandler(){
        this.keysEventMap = new HashMap<>();
    }

    public void addKeyHandler(int keyCode, KeyHandler handler){
        this.keysEventMap.put(keyCode, handler);
    }

    public KeyHandler getHandler(Input input){
        if (this.keysEventMap.size() == 0){
            return null;
        }

        for (Integer key : this.keysEventMap.keySet()){
            if (input.isKeyDown(key)){
                return this.keysEventMap.get(key);
            }
        }

        return null;
    }

    /**
     * Execute the key event
     * @param args arguments for execute of key event
     */
    @Override
    public void onHandle(EventArgs args) {
        try{
            KeyEventArgs params = (KeyEventArgs)args;
            if (params.getSystemObjectScope() == null){
                throw new IllegalArgumentException("Object scope doesn't exists");
            }

            if (params.getHandler() == null){
                return;
            }

            params.getHandler().execute(params.getSystemObjectScope());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface KeyHandler{
        void execute(ProxyScope.ScopeStorage storage);
    }
}

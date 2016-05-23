package com.story.game.action;

import com.story.core.actions.EventArgs;
import com.story.game.action.eventArgs.GameEventArgs;
import com.story.game.action.eventArgs.KeyEventArgs;
import com.story.modules.global.ActionType;

/**
 * Created by alex on 05.05.16.
 */
public class KeyActionHandler implements IKeyAction {
    private KeyEventHandler keyEventHandler;

    @Override
    public void addKeyEventListener(int keyCode, KeyEventHandler.KeyHandler handler){
        if (this.keyEventHandler == null){
            this.keyEventHandler = new KeyEventHandler();
        }

        this.keyEventHandler.addKeyHandler(keyCode, handler);
    }

    /**
     * Execute the key event if them event listener was added to keyEventHandler object
     * @param args the game event arguments
     */
    private void executeKeyEvent(GameEventArgs args){
        if ((args.getInput() == null) || (args.getSystemObjectScope() == null)){
            throw new IllegalArgumentException("Incorrect GameEventArgs object");
        }

        KeyEventHandler.KeyHandler handler = this.keyEventHandler.getHandler(args.getInput());
        if (handler == null){
            return;
        }

        KeyEventArgs params = new KeyEventArgs(ActionType.KEY_PRESSED);
        params.setHandler(handler);
        params.setSystemObjectScope(args.getSystemObjectScope());

        this.keyEventHandler.onHandle(params);
    }

    @Override
    public void onHandle(EventArgs args) {
        try{
            GameEventArgs params = (GameEventArgs)args;
            switch (params.type){
                case KEY_PRESSED:
                    this.executeKeyEvent(params);
                    break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

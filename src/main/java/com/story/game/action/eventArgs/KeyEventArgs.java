package com.story.game.action.eventArgs;

import com.story.core.actions.EventArgs;
import com.story.game.action.KeyEventHandler;
import com.story.modules.global.ActionType;

/**
 * Created by alex on 07.05.16.
 */
public class KeyEventArgs extends EventArgs {
    private KeyEventHandler.KeyHandler handler;

    public KeyEventArgs(ActionType type) {
        super(type);
    }

    public KeyEventHandler.KeyHandler getHandler() {
        return handler;
    }

    public void setHandler(KeyEventHandler.KeyHandler handler) {
        this.handler = handler;
    }
}

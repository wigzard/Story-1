package com.story.game.handlers.eventArgs;

import com.story.core.actions.EventArgs;
import com.story.game.handlers.KeyEventHandler;
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

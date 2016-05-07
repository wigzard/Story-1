package com.story.game.eventArgs;

import com.story.core.coreHandlers.EventArgs;
import com.story.game.handlers.action.KeyEventHandler;
import com.story.game.storages.ProxyScope;
import com.story.modules.global.ActionType;

/**
 * Created by alex on 07.05.16.
 */
public class KeyEventArgs extends EventArgs {
    private ProxyScope.ScopeStorage systemObjectScope;
    private KeyEventHandler.KeyHandler handler;

    public KeyEventArgs(ActionType type) {
        super(type);
    }

    public void setSystemObjectScope(ProxyScope.ScopeStorage storage){
        this.systemObjectScope = storage;
    }

    public ProxyScope.ScopeStorage getSystemObjectScope() {
        return systemObjectScope;
    }

    public KeyEventHandler.KeyHandler getHandler() {
        return handler;
    }

    public void setHandler(KeyEventHandler.KeyHandler handler) {
        this.handler = handler;
    }
}

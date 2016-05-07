package com.story.game.eventArgs;

import com.story.core.coreHandlers.EventArgs;
import com.story.game.storages.ProxyScope;
import com.story.modules.global.ActionType;
import org.newdawn.slick.Input;

/**
 * Created by alex on 05.05.16.
 */
public class GameEventArgs extends EventArgs {
    private ProxyScope.ScopeStorage systemObjectScope;
    private Input input;

    public GameEventArgs(ActionType type) {
        super(type);
    }

    public void setSystemObjectScope(ProxyScope.ScopeStorage storage){
        this.systemObjectScope = storage;
    }

    public ProxyScope.ScopeStorage getSystemObjectScope() {
        return systemObjectScope;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}

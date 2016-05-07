package com.story.game.mediators;

import com.story.game.handlers.action.IActionHandler;
import com.story.game.storages.ProxyScope;
import org.newdawn.slick.Input;

/**
 * Created by alex on 04.05.16.
 */
public interface IGameplaymediator {
    ProxyScope.ScopeStorage getStorageScope();
    void ExecuteAction(Input input);
    void setActionHandler(IActionHandler handler);
}

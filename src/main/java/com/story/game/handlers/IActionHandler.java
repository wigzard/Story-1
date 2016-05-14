package com.story.game.handlers;

import com.story.core.coreHandlers.IEventHandler;
import com.story.game.handlers.action.KeyEventHandler;

/**
 * Created by alex on 05.05.16.
 */
public interface IActionHandler extends IEventHandler {
    void addKeyEventListener(int keyCode, KeyEventHandler.KeyHandler handler);
}

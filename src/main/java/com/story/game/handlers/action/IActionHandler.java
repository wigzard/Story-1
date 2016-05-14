package com.story.game.handlers.action;

import com.story.core.coreHandlers.IEventHandler;

/**
 * Created by alex on 05.05.16.
 */
public interface IActionHandler extends IEventHandler {
    void addKeyEventListener(int keyCode, KeyEventHandler.KeyHandler handler);
}

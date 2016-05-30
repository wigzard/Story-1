package com.story.game.handlers;

import com.story.core.actions.IEventHandler;

/**
 * Created by alex on 05.05.16.
 */
public interface IKeyAction extends IEventHandler {
    void addKeyEventListener(int keyCode, KeyEventHandler.KeyHandler handler);
}

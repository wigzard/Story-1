package com.story.game.builders;

import com.story.game.handlers.IActionHandler;
import com.story.game.mediators.IGameplaymediator;

/**
 * Created by alex on 09.05.16.
 */
public interface IGameplayBuilder {
    void createInstance();
    void createKeyEvents(IActionHandler handler);
    IGameplaymediator getInstance();
}

package com.story.game.builders;

import com.story.game.action.IKeyAction;
import com.story.game.mediators.IGameplaymediator;

/**
 * Created by alex on 09.05.16.
 */
public interface IGameplayBuilder {
    void createInstance();
    void createKeyEvents(IKeyAction handler);
    IGameplaymediator getInstance();
}

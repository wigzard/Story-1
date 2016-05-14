package com.story.game.builders;

import com.story.game.handlers.action.IActionHandler;
import com.story.game.mediators.IGameplaymediator;

/**
 * Created by alex on 09.05.16.
 */
public class GameplayFactory {
    public static IGameplaymediator createMediator(IActionHandler actionHandler){
        IGameplayBuilder builder = new GameplayBuilder();

        builder.createInstance();
        builder.createKeyEvents(actionHandler);
        return builder.getInstance();
    }
}

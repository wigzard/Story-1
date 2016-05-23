package com.story.game.builders;

import com.story.game.action.IKeyAction;
import com.story.game.mediators.IGameplaymediator;

/**
 * Created by alex on 09.05.16.
 */
public class GameplayFactory {
    public static IGameplaymediator createMediator(IKeyAction actionHandler){
        IGameplayBuilder builder = new GameplayBuilder();

        builder.createInstance();
        builder.createKeyEvents(actionHandler);
        return builder.getInstance();
    }
}

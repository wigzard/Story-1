package com.story.game.factories;

import com.story.game.action.IKeyAction;
import com.story.game.action.KeyActionHandler;

/**
 * Created by alex on 14.05.16.
 */
public class ActionFactory {

    /**
     * Create a instance of IKeyAction
     * @return KeyActionHandler
     */
    public static IKeyAction create(){
        return new KeyActionHandler();
    }
}

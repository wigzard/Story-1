package com.story.game.factories;

import com.story.game.handlers.IActionHandler;
import com.story.game.handlers.action.ActionHandler;

/**
 * Created by alex on 14.05.16.
 */
public class ActionFactory {

    /**
     * Create a instance of IActionHandler
     * @return ActionHandler
     */
    public static IActionHandler create(){
        return new ActionHandler();
    }
}

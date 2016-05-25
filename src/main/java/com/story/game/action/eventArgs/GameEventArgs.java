package com.story.game.action.eventArgs;

import com.story.core.actions.EventArgs;
import com.story.modules.global.ActionType;
import org.newdawn.slick.Input;

/**
 * Created by alex on 05.05.16.
 */
public class GameEventArgs extends EventArgs {
    private Input input;

    public GameEventArgs(ActionType type) {
        super(type);
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }
}

package com.story.core.actions;

import com.story.modules.global.ActionType;

/**
 * Created by alex on 05.05.16.
 */
public class EventArgs {
    public ActionType type;

    public EventArgs(ActionType type){
        this.type = type;
    }
}

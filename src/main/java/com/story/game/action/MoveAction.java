package com.story.game.action;

import com.story.core.actions.EventArgs;
import com.story.core.actions.IEventHandler;

/**
 * Created by alex on 19.05.16.
 */
public class MoveAction{

    public MoveAction(String name) {
    }

    protected void execute() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void onHandle(EventArgs args) {

    }
}

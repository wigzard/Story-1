package com.story.game.handlers.eventArgs;

import com.story.core.actions.EventArgs;
import com.story.core.entities.Npc;
import com.story.modules.global.ActionType;

import java.util.List;

/**
 * Created by alex on 30.05.16.
 */
public class NpcMoveEventArgs extends EventArgs {
    public List<Npc> listOfNpc;

    public NpcMoveEventArgs() {
        super(ActionType.MOVE);
    }
}

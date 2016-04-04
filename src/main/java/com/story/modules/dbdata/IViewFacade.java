package com.story.modules.dbdata;

import com.story.modules.dbdata.view.map.Map;
import com.story.modules.dbdata.view.other.OtherObject;
import com.story.modules.dbdata.view.player.Player;

/**
 * Created by alex on 29.03.16.
 */
public interface IViewFacade {
    Map getMap(int mapId);
    Player getPlayer(int playerId);
    OtherObject getOtherObject();
}

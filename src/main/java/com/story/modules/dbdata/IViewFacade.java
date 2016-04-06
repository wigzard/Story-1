package com.story.modules.dbdata;

import com.story.modules.dbdata.view.map.Map;
import com.story.modules.dbdata.view.other.OtherObject;
import com.story.modules.dbdata.view.player.Person;

/**
 * Created by alex on 29.03.16.
 */
public interface IViewFacade {
    Map getMap(int mapId);
    Person getPlayer(int playerId);
    OtherObject getOtherObject();
}

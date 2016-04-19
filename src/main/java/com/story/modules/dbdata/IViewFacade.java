package com.story.modules.dbdata;

import com.story.modules.dbdata.view.MapData;
import com.story.modules.dbdata.view.PersonData;
import com.story.modules.dbdata.view.other.OtherObject;

/**
 * Created by alex on 29.03.16.
 */
public interface IViewFacade {
    MapData getMap(int mapId);
    PersonData getPlayer(int playerId);
    OtherObject getOtherObject();
}

package com.story.modules.dbdata;

import com.story.modules.dbdata.view.MapDescriptor;
import com.story.modules.dbdata.view.PersonDescriptor;
import com.story.modules.dbdata.view.other.OtherObject;

/**
 * Created by alex on 29.03.16.
 */
public interface IViewFacade {
    MapDescriptor getMap(int mapId);
    PersonDescriptor getPlayer(int playerId);
    OtherObject getOtherObject();
}

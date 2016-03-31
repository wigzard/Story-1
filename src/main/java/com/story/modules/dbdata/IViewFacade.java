package com.story.modules.dbdata;

import com.story.modules.dbdata.view.Map;
import com.story.modules.dbdata.view.OtherObject;
import com.story.modules.dbdata.view.Player;

/**
 * Created by alex on 29.03.16.
 */
public interface IViewFacade {
    Map getMap(int mapId);
    Player getPlayer();
    OtherObject getOtherObject();
}

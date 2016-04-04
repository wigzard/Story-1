package com.story.modules.dbdata.managers;

import com.story.modules.dbdata.view.IMapData;
import com.story.modules.dbdata.view.other.OtherObject;

/**
 * Created by alex on 29.03.16.
 */
public class OtherObjectsManager implements IManager {
    @Override
    public IMapData getData(int id) {
        return new OtherObject();
    }
}

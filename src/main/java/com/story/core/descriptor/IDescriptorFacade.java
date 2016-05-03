package com.story.core.descriptor;

import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.dbdata.descriptor.ObjectDescriptor;

/**
 * Created by alex on 29.03.16.
 */
public interface IDescriptorFacade {
    MapDescriptor getMap(int mapId);
    PersonDescriptor getPlayer(int playerId);
    ObjectDescriptor getOtherObject();
}

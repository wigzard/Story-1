package com.story.core.descriptor;

import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.dbdata.descriptor.PersonDescriptor;

import java.util.List;

/**
 * Created by alex on 29.03.16.
 */
public interface IDescriptorFacade {
    MapDescriptor getMap(int mapId);
    PersonDescriptor getPlayer(int playerId);
    List<PersonDescriptor> getNPCDescriptor(int[] descriptorIds);
}

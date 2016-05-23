package com.story.game.components.map;

import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.entities.Npc;
import com.story.core.entities.Player;
import com.story.game.factories.ComponentFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 23.05.16.
 */
public class MapBuilder {
    private static MapBuilder instance;

    private IDescriptorFacade facade;

    private MapBuilder(){}

    private void setFacade(IDescriptorFacade facade){
        this.facade = facade;
    }

    /**
     * Create the instance of AbstractMap
     */
    private AbstractMap createMap(int mapDescriptorId) throws SlickException {
        return new Map(this.facade.getMap(mapDescriptorId));
    }

    /**
     * Create the player on map by descriptorId
     * @param descriptorId descriptor id
     * @return new instance of player
     */
    private Player createPlayer(int descriptorId){
        return ComponentFactory.createPlayerComponent(this.facade.getPlayer(descriptorId));
    }

    /**
     * Create the list of simple npc
     * @param descriptorIds
     * @return
     */
    private List<Npc> createSimpleNpc(int[] descriptorIds){
        if ((descriptorIds == null) || (descriptorIds.length == 0)){
            return null;
        }

        List<Npc> result = new ArrayList<>();

        for (PersonDescriptor descriptor: this.facade.getNPCDescriptor(descriptorIds)) {
            result.add(ComponentFactory.createSimpleNPCComponent(descriptor));
        }

        return result;
    }

    private AbstractMap createMapComponent(int mapDescriptorId,
                                  int playerDescriptorId,
                                  int[] npcDescriptorIds) throws SlickException {
        if (this.facade == null){
            return null;
        }

        AbstractMap map = this.createMap(mapDescriptorId);
        map.setPlayerComponent(this.createPlayer(playerDescriptorId));
        map.addSimpleNpc(this.createSimpleNpc(npcDescriptorIds));

        return map;
    }

    /**
     * Build the instance of abstract map
     */
    public static AbstractMap createMap(IDescriptorFacade facade,
                                        int mapDescriptorId,
                                        int playerDescriptorId,
                                        int[] npcDescriptorIds){
        if (instance == null){
            instance = new MapBuilder();
        }

        instance.setFacade(facade);
        try {
            return instance.createMapComponent(mapDescriptorId, playerDescriptorId, npcDescriptorIds);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        return null;
    }
}

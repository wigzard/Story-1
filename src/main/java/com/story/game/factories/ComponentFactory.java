package com.story.game.factories;

import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.entities.Npc;
import com.story.core.entities.Player;
import com.story.game.components.PlayerComponent;
import com.story.game.components.SimpleNpcComponent;
import com.story.game.components.map.AbstractMap;
import com.story.game.components.map.MapBuilder;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 14.05.16.
 */
public class ComponentFactory {

    /**
     * Create the player handler
     * @param descriptor player descriptor
     * @return PlayerComponent instance
     */
    public static Player createPlayerComponent(PersonDescriptor descriptor){
        try {
            return new PlayerComponent(descriptor);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Create the instance of AbstractMap
     * @param facade the facade for data
     * @param mapDescriptorId the map descriptor id
     * @param playerDescriptorId the player descriptor id
     * @param npcDescriptorIds the npc descriptors id
     * @return instance of AbstractMap
     */
    public static AbstractMap createMapComponent(IDescriptorFacade facade,
                                                 int mapDescriptorId,
                                                 int playerDescriptorId,
                                                 int[] npcDescriptorIds){
        return MapBuilder.createMap(facade, mapDescriptorId, playerDescriptorId, npcDescriptorIds);
    }

    private static int x = 1;
    private static int y = 1;
    public static Npc createSimpleNPCComponent(PersonDescriptor descriptor){
        try {
            return new SimpleNpcComponent(descriptor, new Point(x++, y++));
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return null;
    }
}

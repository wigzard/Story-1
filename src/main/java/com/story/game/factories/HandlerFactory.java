package com.story.game.factories;

import com.story.core.entities.Npc;
import com.story.core.entities.Player;
import com.story.game.components.PlayerComponent;
import com.story.game.components.SimpleNpcComponent;
import com.story.game.components.map.AbstractMap;
import com.story.game.components.map.Map;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 14.05.16.
 */
public class HandlerFactory {

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
     * Create the instance of MapEntity
     * @param descriptor map descriptor
     * @return Map descriptor
     */
    public static AbstractMap createMapComponent(MapDescriptor descriptor){
        try {
            return new Map(descriptor);
        } catch (SlickException e) {
            e.printStackTrace();
        }

        return null;
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

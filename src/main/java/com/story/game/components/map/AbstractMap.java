package com.story.game.components.map;

import com.story.core.entities.Npc;
import com.story.core.entities.Player;
import com.story.core.entities.map.MapEntity;
import com.story.modules.dbdata.descriptor.MapDescriptor;

import java.util.List;

/**
 * Created by alex on 23.05.16.
 */
public abstract class AbstractMap extends MapEntity{
    private MapElements mapComponents;

    public AbstractMap(MapDescriptor map) {
        super(map);
        this.mapComponents = new MapElements();
    }

    /**
     * Get the player component
     * @return player component
     */
    public Player getPlayerComponent(){
        return this.mapComponents.getPlayerComponent();
    }

    /**
     * Set the player component
     * @param component The player component
     */
    public void setPlayerComponent(Player component){
        this.mapComponents.setPlayerComponent(component);
    }

    /**
     * Add the simple npc object to map entities
     * @param component
     */
    public void addSimpleNpc(Npc component){
        this.mapComponents.addSimpleNPC(component);
    }

    /**
     * Get the simple npc list
     * @return The simple npc list
     */
    public List<Npc> getNpcList(){
        return this.mapComponents.getSimpleNPC();
    }
}

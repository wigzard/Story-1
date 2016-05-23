package com.story.game.components.map;

import com.story.core.entities.Npc;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.List;

/**
 * Created by alex on 14.04.16.
 */
public class Map extends ImplementationLevel {

    public Map(MapDescriptor map) throws SlickException {
        super(map);
    }

    /**
     * Init map and all components
     * @throws SlickException
     */
    @Override
    public void init() throws SlickException {
        this.tiledMap = new TiledMap(this.mapDescriptor.getPathToTMX());

        this.borderLayer = this.tiledMap.getLayerIndex(BorderLayerName);
        this.objectLayer = this.tiledMap.getLayerIndex(ObjectsLayerName);

        //Init player component
        if (this.getPlayerComponent() != null) {
            this.getPlayerComponent().init();
        }

        //Init simple npc list
        List<Npc> npcComponents = this.getNpcList();
        if ((npcComponents != null) && (npcComponents.size() > 0)){
            for (Npc component: npcComponents) {
                component.init();
            }
        }
    }
}

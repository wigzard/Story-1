package com.story.game.components.map;

import com.story.core.entities.Npc;
import com.story.core.entities.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 23.05.16.
 */
class MapElements {
    private Player playerComponent;
    private List<Npc> simpleNPC;

    public Player getPlayerComponent() {
        return playerComponent;
    }

    public void setPlayerComponent(Player playerComponent) {
        this.playerComponent = playerComponent;
    }

    public List<Npc> getSimpleNPC() {
        return simpleNPC;
    }

    public void addSimpleNPC(Npc component) {
        if (component == null){
            return;
        }

        if (this.simpleNPC == null){
            this.simpleNPC = new ArrayList<>();
        }

        this.simpleNPC.add(component);
    }

    public void addSimpleNPC(List<Npc> components) {
        if (components == null){
            return;
        }

        if (this.simpleNPC == null){
            this.simpleNPC = new ArrayList<>();
        }

        this.simpleNPC.addAll(components);
    }
}

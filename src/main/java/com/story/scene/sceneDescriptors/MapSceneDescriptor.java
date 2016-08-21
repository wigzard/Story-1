package com.story.scene.sceneDescriptors;

import com.story.scene.components.descriptors.SimpleNpcDescriptor;
import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 01.08.16.
 * Represent data for run the map
 */
public class MapSceneDescriptor extends SceneDescriptor{
    /**
     * The Id of map which should be loaded
     */
    public int mapId;

    /**
     * The start point of player
     */
    public Point playerStartPoint;

    /**
     * The id of player in database
     */
    public int playerId;

    /**
     * The descriptors of npc
     */
    public SimpleNpcDescriptor[] npcDescriptors;

    @Override
    public void dispose() {
        this.playerStartPoint = null;
    }
}

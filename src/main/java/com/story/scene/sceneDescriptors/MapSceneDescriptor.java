package com.story.scene.sceneDescriptors;

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

    @Override
    public void dispose() {
        this.playerStartPoint = null;
    }
}

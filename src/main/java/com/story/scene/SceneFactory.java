package com.story.scene;

import com.story.scene.sceneDescriptors.MapSceneDescriptor;

import java.awt.*;

/**
 * Created by alex on 14.07.16.
 */
public class SceneFactory {
    public static Scene CreateScene(){
        MapSceneDescriptor sceneDescriptor = new MapSceneDescriptor();
        sceneDescriptor.mapId = 1;
        sceneDescriptor.playerStartPoint = new Point(12, 12);
        sceneDescriptor.playerId = 1;

        return new MapScene(sceneDescriptor);
    }
}

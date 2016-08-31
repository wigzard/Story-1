package com.story.scene;

import com.story.scene.components.descriptors.SimpleNpcDescriptor;
import com.story.scene.sceneDescriptors.MapSceneDescriptor;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Created by alex on 14.07.16.
 */
public class SceneFactory {
    public static Scene CreateScene()  {
        MapSceneDescriptor sceneDescriptor = new MapSceneDescriptor();
        sceneDescriptor.mapId = 1;
        sceneDescriptor.playerStartPoint = new Point(12, 12);
        sceneDescriptor.playerId = 1;

        Random random = new Random();
        SimpleNpcDescriptor descriptor1 = new SimpleNpcDescriptor(2);
        SimpleNpcDescriptor descriptor2 = new SimpleNpcDescriptor(3);
        descriptor1.setStartPosition(new Point(13, 13));
        descriptor1.setMoveInterval(random.nextInt(10000 - 3000) + 3000);
        descriptor2.setStartPosition(new Point(17, 17));
        descriptor2.setMoveInterval(random.nextInt(10000 - 3000) + 3000);

        sceneDescriptor.npcDescriptors = new SimpleNpcDescriptor[]{descriptor1, descriptor2};

        return new MapScene(sceneDescriptor);
    }
}

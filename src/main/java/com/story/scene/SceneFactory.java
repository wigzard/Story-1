package com.story.scene;

/**
 * Created by alex on 14.07.16.
 */
public class SceneFactory {
    public static Scene CreateScene(){
        return new MapScene();
    }
}

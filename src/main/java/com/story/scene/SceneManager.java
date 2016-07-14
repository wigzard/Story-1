package com.story.scene;

/**
 * Created by alex on 14.07.16.
 */
public class SceneManager {

    /**
     * Initialize new instance of SceneManager
     */
    public SceneManager(){
    }

    public Scene CreateScene(){
        return new MapScene();
    }
}

package com.story.scene.components.helpers;

import com.story.system.IDisposable;

/**
 * Created by alex on 23.07.16.
 * Helper, which creating animation of actor
 */
public class ActorAnimationHelper implements IDisposable {
    private String url;

    public ActorAnimationHelper(String url){
        this.url = url;
    }



    @Override
    public void dispose() {
        this.url = null;
    }
}

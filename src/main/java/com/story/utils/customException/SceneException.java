package com.story.utils.customException;

/**
 * Created by alex on 14.07.16.
 */
public class SceneException extends Exception {
    public SceneException(String message){
        super(message);
    }

    public SceneException(Throwable e){
        super(e);
    }
}

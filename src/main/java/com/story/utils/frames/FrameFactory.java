package com.story.utils.frames;

/**
 * Created by alex on 26.05.16.
 */
public class FrameFactory {

    /**
     * Create a new instance {@link IFrameStorage}
     * @return instance of {@link IFrameStorage}
     */
    public static IFrameStorage createFrameStorage(){
        return new QueueFrameStorage();
    }

}

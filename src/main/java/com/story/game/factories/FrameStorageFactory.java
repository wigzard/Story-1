package com.story.game.factories;

import com.story.core.frames.IFrameStorage;
import com.story.game.storages.QueueFrameStorage;

/**
 * Created by alex on 14.05.16.
 */
public class FrameStorageFactory {
    /**
     * Create new the IFrame storage
     * @return QueueFrameStorage instance
     */
    public static IFrameStorage create(){
        return new QueueFrameStorage();
    }

}

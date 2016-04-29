package com.story.core.frames;

import java.util.Queue;

/**
 * Created by alex on 28.04.16.
 */
public interface IFrameStorage {
    CentralObject getNextFrameOfCentralObject();
    void addCentralObjectFrame(CentralObject object);
    void addCentralObjectFrames(Queue<CentralObject> objects);
    boolean hasNextFrameOfCentralObject();
}

package com.story.utils.frames;

import java.util.Queue;

/**
 * Created by alex on 28.04.16.
 */
public interface IFrameStorage {
    Frame getNextFrame();
    void addFrame(Frame object);
    void addFrames(Queue<Frame> objects);
    boolean hasNextFrame();
}

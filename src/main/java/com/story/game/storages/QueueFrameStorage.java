package com.story.game.storages;

import com.story.core.frames.Frame;
import com.story.core.frames.IFrameStorage;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by alex on 28.04.16.
 */
public class QueueFrameStorage implements IFrameStorage {
    private Queue<Frame> coQueue = null;

    public QueueFrameStorage(){
        this.coQueue = new LinkedList<>();
    }

    @Override
    public synchronized Frame getNextFrame() {
        return this.coQueue.size() == 0? null : this.coQueue.poll();
    }

    @Override
    public synchronized void addFrame(Frame object) {
        this.coQueue.add(object);
    }

    @Override
    public synchronized void addFrames(Queue<Frame> objects) {
        this.coQueue.addAll(objects);
    }

    @Override
    public synchronized boolean hasNextFrame() {
        return this.coQueue.size() > 0;
    }
}

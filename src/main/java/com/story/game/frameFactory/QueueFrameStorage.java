package com.story.game.frameFactory;

import com.story.core.frames.CentralObject;
import com.story.core.frames.IFrameStorage;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by alex on 28.04.16.
 */
public class QueueFrameStorage implements IFrameStorage {
    private Queue<CentralObject> coQueue = null;

    public QueueFrameStorage(){
        this.coQueue = new LinkedList<>();
    }

    @Override
    public CentralObject getNextFrameOfCentralObject() {
        return this.coQueue.size() == 0? null : this.coQueue.poll();
    }

    @Override
    public void addCentralObjectFrame(CentralObject object) {
        this.coQueue.add(object);
    }

    @Override
    public void addCentralObjectFrames(Queue<CentralObject> objects) {
        this.coQueue.addAll(objects);
    }

    @Override
    public boolean hasNextFrameOfCentralObject() {
        return this.coQueue.size() > 0;
    }
}

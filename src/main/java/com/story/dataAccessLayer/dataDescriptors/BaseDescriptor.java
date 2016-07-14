package com.story.dataAccessLayer.dataDescriptors;

import com.story.system.IDisposable;

/**
 * Created by alex on 13.07.16.
 */
public abstract class BaseDescriptor implements IDisposable{
    protected int id;

    protected BaseDescriptor(int id) {
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}

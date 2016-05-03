package com.story.modules.dbdata.descriptor;

import com.story.core.descriptor.IDescriptor;

/**
 * Created by alex on 30.04.16.
 */
public abstract class DBTableDescriptor implements IDescriptor {
    private int id;

    public DBTableDescriptor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

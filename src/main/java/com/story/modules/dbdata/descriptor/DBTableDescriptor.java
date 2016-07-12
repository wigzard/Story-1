package com.story.modules.dbdata.descriptor;

/**
 * Created by alex on 30.04.16.
 */
public abstract class DBTableDescriptor{
    private int id;

    public DBTableDescriptor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

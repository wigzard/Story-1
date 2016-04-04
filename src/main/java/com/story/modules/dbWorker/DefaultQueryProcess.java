package com.story.modules.dbWorker;

/**
 * Created by alex on 30.03.16.
 */
public abstract class DefaultQueryProcess implements IQueryProcess {
    protected String query;

    public DefaultQueryProcess(String query){
        this.query = query;
    }

    @Override
    public String getQuery() {
        return this.query;
    }
}

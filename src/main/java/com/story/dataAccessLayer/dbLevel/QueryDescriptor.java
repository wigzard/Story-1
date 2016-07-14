package com.story.dataAccessLayer.dbLevel;

import java.sql.ResultSet;
import java.util.function.Function;

/**
 * Created by alex on 30.03.16.
 */
public class QueryDescriptor{
    private String query;
    private Function<ResultSet, Void> respondHandler;

    public QueryDescriptor(String query){
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }

    public Function<ResultSet, Void> getRespondHandler() {
        return respondHandler;
    }

    public void setRespondHandler(Function<ResultSet, Void> respondHandler) {
        this.respondHandler = respondHandler;
    }
}

package com.story.dataAccessLayer.dbLevel;

/**
 * Created by alex on 29.03.16.
 */
public interface IQueryExecutor {
    void selectExecute(QueryDescriptor args);
    boolean updateExecute(QueryDescriptor args);
    boolean insertExecute(QueryDescriptor args);
}

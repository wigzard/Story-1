package com.story.modules.dbWorker;

/**
 * Created by alex on 29.03.16.
 */
public interface IQueryExecutor {
    void selectExecute(IQueryProcess args);
    boolean updateExecute(IQueryProcess args);
    boolean insertExecute(IQueryProcess args);
}

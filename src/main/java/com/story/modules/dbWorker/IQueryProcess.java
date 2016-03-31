package com.story.modules.dbWorker;

import java.sql.ResultSet;
import java.util.HashMap;

/**
 * Created by alex on 29.03.16.
 */
public interface IQueryProcess {
    String getQuery();
    void processData(ResultSet resultSet);
}

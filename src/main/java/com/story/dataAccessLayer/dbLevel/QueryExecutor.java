package com.story.dataAccessLayer.dbLevel;

import com.story.utils.GlobalHelper;
import com.story.utils.log.Trace;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by alex on 29.03.16.
 */
public class QueryExecutor implements IQueryExecutor {
    private Connection connection;

    public QueryExecutor(String url){
        try {
            this.connection = DbConnector.getInstance(url);
        } catch (FileNotFoundException e) {
            this.connection = null;
        }
    }

    @Override
    public void selectExecute(QueryDescriptor descriptor) {
        if (GlobalHelper.isNullOrEmpty(descriptor.getQuery())){
            throw new IllegalArgumentException("Incorrect query");
        }

        try {
            this.connection.setAutoCommit(false);

            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(descriptor.getQuery());

            if (descriptor.getRespondHandler() != null) {
                descriptor.getRespondHandler().apply(resultSet);
            }

            resultSet.close();
            statement.close();
            //this.connection.close();

        } catch (SQLException e) {
            Trace.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateExecute(QueryDescriptor args) {
        return false;
    }

    @Override
    public boolean insertExecute(QueryDescriptor args) {
        return false;
    }
}

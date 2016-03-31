package com.story.modules.dbWorker;

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
    public void selectExecute(IQueryProcess args) {
        if ((args.getQuery() == null) || (args.getQuery().isEmpty())){
            throw new IllegalArgumentException("Incorrect query");
        }

        try {
            this.connection.setAutoCommit(false);

            Statement statement = this.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(args.getQuery());

            args.processData(resultSet);

            resultSet.close();
            statement.close();
            //this.connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateExecute(IQueryProcess args) {
        return false;
    }

    @Override
    public boolean insertExecute(IQueryProcess args) {
        return false;
    }
}

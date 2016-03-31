package com.story.modules.dbWorker;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by alex on 29.03.16.
 */
class DbConnector {
    private static HashMap<String, Connection> connections = new HashMap<>();
    private static DbConnector connector = new DbConnector();

    private Connection conn;

    private DbConnector(){}

    private void createNewConnector(String url){
        try {

            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(String url) throws FileNotFoundException {
        File f = new File(url);
        if(!f.exists()) {
            throw new FileNotFoundException(url);
        }

        this.createNewConnector(url);
        return conn;
    }

    /**
     *
     * @param url this is path to a database
     * @return connection for database
     */
    public static Connection getInstance(String url) throws FileNotFoundException {
        if (connections.containsKey(url)){
            return connections.get(url);
        }

        try {
            connections.put(url, connector.getConnection(url));
        } catch (FileNotFoundException e) {
            throw e;
        }
        return connections.get(url);
    }
}

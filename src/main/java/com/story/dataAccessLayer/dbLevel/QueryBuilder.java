package com.story.dataAccessLayer.dbLevel;

import com.story.system.IDisposable;

/**
 * Created by alex on 14.07.16.
 * Helper for build query to database
 */
public class QueryBuilder implements IDisposable{
    private StringBuilder query;

    /**
     * Initialize new instance of QueryBuilder
     */
    public QueryBuilder(){
        this.query = new StringBuilder();
    }

    public void reset(){
        this.query = new StringBuilder();
    }

    public QueryBuilder select(String[] fields){
        if ((fields == null) || (fields.length == 0)){
            return this.selectAll();
        }

        this.query.append(" ");
        this.query.append(String.join(",", fields));
        this.query.append(" ");
        return this;
    }

    public QueryBuilder selectAll(){
        this.query.append("SELECT * ");
        return this;
    }

    public QueryBuilder from(String table){
        this.query.append("FROM ");
        this.query.append(table);
        this.query.append(" ");

        return this;
    }

    public QueryBuilder where(){
        this.query.append("WHERE ");
        return this;
    }

    public QueryBuilder equals(String field, String value){
        this.query.append(field);
        this.query.append("=");
        this.query.append(value);
        this.query.append(" ");
        return this;
    }

    @Override
    public String toString(){
        return this.query.toString();
    }

    @Override
    public void dispose() {
        this.query = null;
    }
}

package com.story.modules.dbdata.view.map;

import com.story.modules.dbdata.view.IMapData;

import java.util.ArrayList;

/**
 * Created by alex on 29.03.16.
 */
public class Map implements IMapData {
    private int id;
    private String description;
    private String name;
    private String pathToTMX;

    public Map(int id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathToTMX() {
        return pathToTMX;
    }

    public void setPathToTMX(String pathToTMX) {
        this.pathToTMX = pathToTMX;
    }

    public int getId() {
        return id;
    }
}

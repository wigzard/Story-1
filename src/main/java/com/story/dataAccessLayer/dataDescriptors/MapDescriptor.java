package com.story.dataAccessLayer.dataDescriptors;

/**
 * Created by alex on 13.07.16.
 */
public class MapDescriptor extends BaseDescriptor {
    private String description;
    private String name;
    private String pathToTMX;

    public MapDescriptor(int id) {
        super(id);
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

    @Override
    public void dispose() {
        this.name = null;
        this.pathToTMX = null;
        this.description = null;
    }
}

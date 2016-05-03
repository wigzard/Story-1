package com.story.modules.dbdata.descriptor;

/**
 * Created by alex on 29.03.16.
 */
public class MapDescriptor extends DBTableDescriptor {
    public static final String DBTableName = "Maps";
    public static final String DBFieldId = "Id";
    public static final String DBFieldDescription = "Description";
    public static final String DBFieldName = "Name";
    public static final String DBFieldPathToFile = "PathToFile";

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
}

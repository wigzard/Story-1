package com.story.modules.dbdata.view;

/**
 * Created by alex on 29.03.16.
 */
public class PersonData implements IMapData {
    private int id;
    private String name;
    private String pathPersonPictureSet;
    private String pathFacePictureSet;

    public PersonData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathPersonPictureSet() {
        return pathPersonPictureSet;
    }

    public void setPathPersonPictureSet(String pathPersonPictureSet) {
        this.pathPersonPictureSet = pathPersonPictureSet;
    }

    public String getPathFacePictureSet() {
        return pathFacePictureSet;
    }

    public void setPathFacePictureSet(String pathFacePictureSet) {
        this.pathFacePictureSet = pathFacePictureSet;
    }
}

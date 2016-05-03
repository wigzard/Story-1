package com.story.modules.dbdata.descriptor;

import java.util.ArrayList;

/**
 * Created by alex on 29.03.16.
 */
public class PersonDescriptor extends DBTableDescriptor {
    public static final String DBTableName = "Person";
    public static final String DBFieldId = "Id";
    public static final String DBFieldName = "Name";
    public static final String DBFieldPictureDescriptorId = "PictureDescriptorId";
    public static final String DBFieldFacePictureSetField = "PathFacePictureSet";

    private String name;
    private int pictureDescriptorId;
    private String pathFacePictureSet;
    private ArrayList<PictureObjectDescriptor> pictureDescriptors;

    public PersonDescriptor(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathFacePictureSet() {
        return pathFacePictureSet;
    }

    public void setPathFacePictureSet(String pathFacePictureSet) {
        this.pathFacePictureSet = pathFacePictureSet;
    }

    public int getPictureDescriptorId() {
        return pictureDescriptorId;
    }

    public void setPictureDescriptorId(int pictureDescriptorId) {
        this.pictureDescriptorId = pictureDescriptorId;
    }

    public ArrayList<PictureObjectDescriptor> getPictureDescriptors() {
        return pictureDescriptors;
    }

    public void setPictureDescriptors(ArrayList<PictureObjectDescriptor> pictureDescriptors) {
        this.pictureDescriptors = pictureDescriptors;
    }
}

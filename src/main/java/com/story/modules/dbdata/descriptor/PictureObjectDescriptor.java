package com.story.modules.dbdata.descriptor;

import java.awt.*;

/**
 * Created by alex on 30.04.16.
 */
public class PictureObjectDescriptor extends DBTableDescriptor {
    public static final String DBTableName = "Picture.ObjectDescriptor";
    public static final String DBFieldId = "Id";
    public static final String DBFieldTileWidth = "TileWidth";
    public static final String DBFieldTileHeight = "TileHeight";
    public static final String DBFieldCountOfFrame = "CountOfFrame";
    public static final String DBFieldImagePath = "ImagePath";
    public static final String DBFieldStartColumn = "StartColumn";
    public static final String DBFieldStartRow = "StartRow";
    public static final String DBFieldDefaultColumn = "DefaultColumn";
    public static final String DBFieldDefaultRow = "DefaultRow";

    private int tileWidth;
    private int tileHeight;
    private int countOfFrame;
    private Point startPosition;
    private Point defaultPosition;
    private String imagePath;
    private PersonPictureDescriptor.ObjectSide side;

    public PictureObjectDescriptor(int id) {
        super(id);
    }


    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getCountOfFrame() {
        return countOfFrame;
    }

    public void setCountOfFrame(int countOfFrame) {
        this.countOfFrame = countOfFrame;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int x, int y) {
        this.startPosition = new Point(x, y);
    }

    public Point getDefaultPosition() {
        return defaultPosition;
    }

    public void setDefaultPosition(int x, int y) {
        this.defaultPosition = new Point(x, y);
    }

    public PersonPictureDescriptor.ObjectSide getSide() {
        return side;
    }

    public void setSide(PersonPictureDescriptor.ObjectSide side) {
        this.side = side;
    }
}

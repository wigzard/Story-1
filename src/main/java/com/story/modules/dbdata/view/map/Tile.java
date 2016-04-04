package com.story.modules.dbdata.view.map;

/**
 * Created by alex on 30.03.16.
 */
public class Tile {
    private int id;
    private String src;
    private int width;
    private int height;

    public Tile(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

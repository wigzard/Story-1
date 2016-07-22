package com.story.utils;

/**
 * Created by alex on 16.07.16.
 */
public class Size {

    private int width;
    private int height;

    public Size(){
        this.width = 0;
        this.height = 0;
    }

    public Size(int width, int height){
        this.width = width;
        this.height = height;
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

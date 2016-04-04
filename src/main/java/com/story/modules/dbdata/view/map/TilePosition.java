package com.story.modules.dbdata.view.map;

/**
 * Created by alex on 31.03.16.
 */
public class TilePosition {
    private int id;
    private int x;
    private int y;
    private int tileId;

    public TilePosition(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getTileId() {
        return tileId;
    }

    public void setTileId(int tileId) {
        this.tileId = tileId;
    }
}

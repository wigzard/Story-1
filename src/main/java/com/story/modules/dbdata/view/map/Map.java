package com.story.modules.dbdata.view.map;

import com.story.modules.dbdata.view.IMapData;

import java.util.ArrayList;

/**
 * Created by alex on 29.03.16.
 */
public class Map implements IMapData {
    private int id;
    private int width;
    private int height;
    private int defaultTileId;
    private int tilesWidth;
    private int tilesHeight;
    private String description;
    private String name;
    private ArrayList<TilePosition> otherTilePositions;
    private ArrayList<Tile> allTiles;

    public Map(int id){
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDefaultTileId() {
        return defaultTileId;
    }

    public void setDefaultTileId(int defaultTileId) {
        this.defaultTileId = defaultTileId;
    }

    public int getTilesWidth() {
        return tilesWidth;
    }

    public void setTilesWidth(int tilesWidth) {
        this.tilesWidth = tilesWidth;
    }

    public int getTilesHeight() {
        return tilesHeight;
    }

    public void setTilesHeight(int tilesHeight) {
        this.tilesHeight = tilesHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TilePosition> getOtherTilePositions() {
        return otherTilePositions;
    }

    public Tile getTile(int tileId){
        if (this.allTiles == null){
            return null;
        }

        for (Tile t: this.allTiles) {
            if (t.getId() == tileId) {
                return t;
            }
        }

        return null;
    }

    public void setOtherTilePositions(ArrayList<TilePosition> otherTilePositions) {
        this.otherTilePositions = otherTilePositions;
    }

    public void setTiles(ArrayList<Tile> allTiles) {
        this.allTiles = allTiles;
    }
}

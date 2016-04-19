package com.story;

import org.newdawn.slick.*;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by alex on 04.04.16.
 */
public class GameSlick extends BasicGame{
    private TiledMap map;
    private int playerX, playerY;
    private int centerX, centerY;//position of player
    private int mapX, mapY;
    private int windowWidth, windowHeight;
    private int tileWidth, tileHeight;

    public GameSlick(int w, int h)
    {
        super("Story");
        this.windowWidth = w;
        this.windowHeight = h;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.map = new TiledMap("resources/1.tmx");
        this.tileWidth = map.getTileWidth();
        this.tileHeight = map.getTileHeight();

        String s = this.map.getLayerProperty(this.map.getLayerIndex("Border"), "count", "0");
        this.centerX = this.windowWidth / this.tileWidth / 2;
        this.centerY = this.windowHeight / this.tileHeight / 2;
        this.playerX = Integer.valueOf(s);
        this.playerY = Integer.valueOf(s);
        mapX -= this.playerX - this.windowWidth / this.tileWidth / 2;
        mapY -= this.playerY - this.windowHeight / this.tileHeight / 2;
    }

    @Override
    public void update(GameContainer gc, int t) throws SlickException {
        if ((gc.getInput().isKeyPressed(Input.KEY_LEFT)) && (this.isCanMove(Input.KEY_LEFT))){
            playerX--;
            mapX++;
        }

        if ((gc.getInput().isKeyPressed(Input.KEY_RIGHT)) && (this.isCanMove(Input.KEY_RIGHT))){
            playerX++;
            mapX--;
        }

        if ((gc.getInput().isKeyPressed(Input.KEY_UP)) && (this.isCanMove(Input.KEY_UP))){
            playerY--;
            mapY++;
        }

        if ((gc.getInput().isKeyPressed(Input.KEY_DOWN)) && (this.isCanMove(Input.KEY_DOWN))){
            playerY++;
            mapY--;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        this.map.render(this.mapX * this.tileWidth, this.mapY * this.tileHeight,
                0, 0,
                this.map.getWidth(), this.map.getHeight());
        g.fillRect(this.centerX * this.tileWidth, this.centerY * this.tileHeight,
                this.tileWidth, this.tileHeight);
    }

    private boolean isCanMove(int keyCode){
        int borderLayer = this.map.getLayerIndex("Border");
        int objectLayer = this.map.getLayerIndex("Objects");

        switch (keyCode){
            case Input.KEY_UP:
                return this.map.getTileId(playerX, playerY - 1, borderLayer) == 0 &&
                        this.map.getTileId(playerX, playerY - 1, objectLayer) == 0;
            case Input.KEY_DOWN:
                return this.map.getTileId(playerX, playerY + 1, borderLayer) == 0 &&
                        this.map.getTileId(playerX, playerY + 1, objectLayer) == 0;
            case Input.KEY_LEFT:
                return this.map.getTileId(playerX - 1, playerY, borderLayer) == 0 &&
                        this.map.getTileId(playerX - 1, playerY, objectLayer) == 0;
            case Input.KEY_RIGHT:
                return this.map.getTileId(playerX + 1, playerY, borderLayer) == 0 &&
                        this.map.getTileId(playerX + 1, playerY, objectLayer) == 0;
        }
        return false;
    }
}

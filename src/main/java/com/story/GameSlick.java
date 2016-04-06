package com.story;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by alex on 04.04.16.
 */
public class GameSlick extends BasicGame{
    private TiledMap map;
    private int x, y;
    private int mapX, mapY;

    public GameSlick() {
        super("one class barebone game");
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new TiledMap("resources/1.tmx");

        x = 1;
        y = 1;
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        int layer = this.map.getLayerIndex("Background");

        if (gc.getInput().isKeyPressed(Input.KEY_RIGHT)){
            if (map.getTileId(x + 1, y, layer) != 0){
                x++;
                System.out.println(map.getTileHeight());
            }
        }

        if (gc.getInput().isKeyPressed(Input.KEY_LEFT)){
            if (map.getTileId(x - 1, y, layer) != 0){
                x--;
            }
        }

        if (gc.getInput().isKeyPressed(Input.KEY_DOWN)){
            if (map.getTileId(y + 1, y, layer) != 0){
                y++;
            }
        }

        if (gc.getInput().isKeyPressed(Input.KEY_UP)){
            if (map.getTileId(y - 1, y, layer) != 0){
                y--;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        this.map.render(x * 32, y * 32, mapX, mapY, mapX + this.map.getWidth(), mapY + this.map.getHeight());
        g.fillRect(10 * 32, 10 * 32, 32, 32);
        //g.drawString("Howdy!", 10, 10);
    }
}

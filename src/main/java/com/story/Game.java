package com.story;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Created by alex on 12.07.16.
 */
public class Game extends BasicGame {
    private TiledMap map;

    public Game() {
        super("Simple test");
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.map = new TiledMap("/home/alex/local_D/projects/IdeaProjects/Story/resources/1.tmx");
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.map.render(0, 0);
    }
}

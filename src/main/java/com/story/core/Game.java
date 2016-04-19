package com.story.core;

import com.story.core.baseHandlers.MapHandler;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public final class Game extends BasicGame {
    private static Game game = null;

    private IGameMediator mediator;

    private Game(String title, IGameMediator mediator) {
        super(title);
        this.mediator = mediator;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.mediator.init();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        this.mediator.executeKeyEvent(gc.getInput());
    }

    @Override
    public void render(GameContainer gc, Graphics graphics) throws SlickException {
        MapHandler handler = this.mediator.getMapHandler();
        Point playerCoordinates = this.mediator.getPlayerCoordinates();

        handler.getTiledMap().render(handler.getMapPosition().x * handler.getTiledMap().getTileWidth(),
                handler.getMapPosition().y * handler.getTiledMap().getTileHeight());
        graphics.fillRect(playerCoordinates.x,
                playerCoordinates.y,
                handler.getTiledMap().getTileWidth(),
                handler.getTiledMap().getTileHeight());
    }

    public static Game getInstance(String title, IGameMediator mediator){
        if (game == null){
            game = new Game(title, mediator);
        }
        else {
            game.mediator = mediator;
        }

        return game;
    }
}

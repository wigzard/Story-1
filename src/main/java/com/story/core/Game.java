package com.story.core;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

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
        this.mediator.init(gameContainer);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        this.mediator.update(gc, i);
    }

    @Override
    public void render(GameContainer gc, Graphics graphics) throws SlickException {
        this.mediator.render(gc, graphics);
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

package com.story.core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 09.04.16.
 */
public interface IGameMediator {
    void render(GameContainer gc, Graphics g);
    void update(GameContainer gc, int delta);
    void init(GameContainer gc) throws SlickException;
}

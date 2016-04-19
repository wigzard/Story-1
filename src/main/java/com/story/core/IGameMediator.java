package com.story.core;

import com.story.core.baseHandlers.MapHandler;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public interface IGameMediator {
    MapHandler getMapHandler();
    Point getPlayerCoordinates();
    void executeKeyEvent(Input input);
    void init() throws SlickException;
}

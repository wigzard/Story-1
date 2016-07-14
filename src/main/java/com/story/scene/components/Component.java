package com.story.scene.components;

import com.story.system.IDisposable;
import com.story.utils.customException.InvalidDescriptor;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 14.07.16.
 * Represent component for scene
 */
public abstract class Component implements IDisposable{

    /**
     * Initialize the component
     */
    public abstract void init() throws SlickException;

    /**
     * Update the game logic here. No rendering should take place in this method though it won't do any harm.
     * @param gameContainer The container holing this game
     * @param delta The amount of time thats passed since last update in milliseconds
     */
    public abstract void update(GameContainer gameContainer, int delta);

    /**
     * Render the game's scene here.                                
     * @param gameContainer The container holing this game                                
     * @param graphics The graphics context that can be used to render. However, normal rendering routines can also be used.                                                                                                       
     */
    public abstract void render(GameContainer gameContainer, Graphics graphics);
}

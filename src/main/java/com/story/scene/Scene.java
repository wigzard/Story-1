package com.story.scene;

import com.story.scene.components.Component;
import com.story.system.IDisposable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by alex on 14.07.16.
 * Base class of scene type
 */
public abstract class Scene implements IDisposable {
    /**
     * List of components which scene should be updated
     */
    protected ArrayList<Component> sceneComponents;

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
     * Render the game's scene here.
     * @param gameContainer The container holing this game
     * @param graphics The graphics context that can be used to render. However, normal rendering routines can also be used.
     */
    public abstract void render(GameContainer gameContainer, Graphics graphics);

    @Override
    public void dispose(){
        if ((this.sceneComponents != null) && (this.sceneComponents.size() > 0)){
            this.sceneComponents.forEach(IDisposable::dispose);
        }

        this.sceneComponents = null;
    }
}

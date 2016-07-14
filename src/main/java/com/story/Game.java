package com.story;

import com.story.scene.Scene;
import com.story.scene.SceneManager;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 12.07.16.
 */
public class Game extends BasicGame {
    private SceneManager manager;
    private Scene scene;

    public Game() {
        super("Simple test");
        this.manager = new SceneManager();
        this.scene = manager.CreateScene();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.scene.init();
    }

    /**
     * Update the game logic here. No rendering should take place in this method though it won't do any harm.                                                                                                        
     * @param gameContainer The container holing this game                                
     * @param delta The amount of time thats passed since last update in milliseconds                                                                   
     * @throws SlickException Throw to indicate an internal error                                     
     */
    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        this.scene.update(gameContainer, delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        this.scene.render(gameContainer, graphics);
    }
}

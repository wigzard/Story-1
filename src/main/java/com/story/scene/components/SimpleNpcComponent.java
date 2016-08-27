package com.story.scene.components;

import com.story.scene.components.descriptors.SimpleNpcDescriptor;
import com.story.scene.components.managers.SimpleNpcComponentManager;
import com.story.utils.customException.InvalidDescriptor;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 06.08.16.
 * Represent the simple npc component
 */
public class SimpleNpcComponent extends ActorComponent {

    /**
     * The manager of component
     */
    private SimpleNpcComponentManager componentManager;

    /**
     * Initialize new instance of {@link SimpleNpcComponent}
     * @param descriptor the descriptor of simple npc
     */
    public SimpleNpcComponent(SimpleNpcDescriptor descriptor){
        this.componentManager = new SimpleNpcComponentManager(descriptor);
    }

    public void changePosition(Point globalViewerStartPoint){
        this.componentManager.updateGlobalPosition(globalViewerStartPoint);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException, InvalidDescriptor {}

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.componentManager.updateNpc(delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.componentManager.drawNpc();
    }

    @Override
    public void dispose() {
        super.dispose();

        if (this.componentManager != null){
            this.componentManager.dispose();
        }
    }
}

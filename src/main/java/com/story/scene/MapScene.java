package com.story.scene;

import com.story.dataAccessLayer.dataActions.RetrieveMapsAction;
import com.story.scene.components.MapComponent;
import com.story.utils.customException.InvalidDescriptor;
import com.story.utils.customException.SceneException;
import com.story.utils.log.Trace;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by alex on 14.07.16.
 * Represent screen as map where player can be moved and execute something actions
 */
public class MapScene extends Scene {

    /**
     * Initialize new instance of MapScene
     */
    public MapScene(){
        this.createComponents();
    }

    /**
     * Create component which should be in scene
     */
    private void createComponents(){
        this.sceneComponents = new ArrayList<>();
        RetrieveMapsAction action = new RetrieveMapsAction();
        try {
            this.sceneComponents.add(new MapComponent(action.RetrieveObjectById(1)));
        } catch (InvalidDescriptor e) {
            Trace.error(e.getMessage(), e);
        }
    }

    @Override
    public void init() throws SlickException {
        this.sceneComponents.forEach(component -> {
            try {
                component.init();
            } catch (SlickException e) {
                SceneException se = new SceneException(e);
                Trace.error(se.getMessage(), se);
            }
        });
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.sceneComponents.forEach(component -> component.update(gameContainer, delta));
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.sceneComponents.forEach(component -> component.render(gameContainer, graphics));
    }

    @Override
    public void dispose(){}
}

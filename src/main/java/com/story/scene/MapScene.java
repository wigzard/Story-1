package com.story.scene;

import com.story.scene.components.SimpleNpcComponent;
import com.story.scene.components.helpers.ComponentAction;
import com.story.scene.managers.MapSceneManager;
import com.story.scene.sceneDescriptors.MapSceneDescriptor;
import com.story.scene.sceneDescriptors.SceneDescriptor;
import com.story.utils.customException.SceneException;
import com.story.utils.events.Event;
import com.story.utils.events.EventType;
import com.story.utils.log.Trace;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 14.07.16.
 * Represent screen as map where player can moved and execute something actions
 */
class MapScene extends Scene {
    private static final String MapChangeEventName = "MapChange";

    /**
     * The manager which describe the behavior of MapScene
     */
    private MapSceneManager mapSceneManager;

    /**
     * Initialize new instance of MapScene
     */
    MapScene(SceneDescriptor descriptor){
        super(descriptor);
        this.initialize();
    }

    /**
     * Initialize component
     */
    private void initialize(){
        this.eventList.addEvent(EventType.SceneReinit, new Event(EventType.SceneReinit));
        this.eventList.addEvent(EventType.SceneRecreate, new Event(EventType.SceneRecreate));

        if (this.sceneDescriptor instanceof MapSceneDescriptor) {
            this.mapSceneManager = new MapSceneManager((MapSceneDescriptor)this.sceneDescriptor);
        }
        else {
            throw new IllegalArgumentException("The scene descriptor should have type of MapSceneDescriptor");
        }
    }

    /**
     * Method, which called when map change event happened
     */
    private Void onMapChange(Void v){
        System.out.println("Called onMapRecreate");
        this.eventList.get(EventType.SceneReinit).notifySubscribers();
        return null;
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            this.mapSceneManager.getMapComponent().init(gameContainer);
            this.mapSceneManager.getMapComponent().addEventListener(EventType.MapRecreate, MapChangeEventName, this::onMapChange);

            this.mapSceneManager.getPlayerComponent().init(gameContainer);

            for (SimpleNpcComponent component: this.mapSceneManager.getSimpleNpcList()){
                component.init(gameContainer);
            }

            for (SimpleNpcComponent component: this.mapSceneManager.getSimpleNpcList()){
                component.changePosition(this.mapSceneManager.getMapComponent().getGlobalPoint());
            }
        } catch (Exception e) {
            SceneException se = new SceneException(e);
            Trace.error(se.getMessage(), se);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        if (gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)){
            this.mapSceneManager.executeKeyAction(ComponentAction.MOVE_RIGHT);
        }
        else if (gameContainer.getInput().isKeyDown(Input.KEY_LEFT)){
            this.mapSceneManager.executeKeyAction(ComponentAction.MOVE_LEFT);
        }
        else if (gameContainer.getInput().isKeyDown(Input.KEY_UP)){
            this.mapSceneManager.executeKeyAction(ComponentAction.MOVE_UP);
        }
        else if (gameContainer.getInput().isKeyDown(Input.KEY_DOWN)){
            this.mapSceneManager.executeKeyAction(ComponentAction.MOVE_DOWN);
        }

        this.mapSceneManager.getMapComponent().update(gameContainer, delta);
        this.mapSceneManager.getPlayerComponent().update(gameContainer, delta);
        for (SimpleNpcComponent component: this.mapSceneManager.getSimpleNpcList()){
            component.changePosition(this.mapSceneManager.getMapComponent().getGlobalPoint());
            component.update(gameContainer, delta);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.mapSceneManager.getMapComponent().render(gameContainer, graphics);
        this.mapSceneManager.getPlayerComponent().render(gameContainer, graphics);

        for (SimpleNpcComponent component: this.mapSceneManager.getSimpleNpcList()){
            component.render(gameContainer, graphics);
        }
    }

    @Override
    public void dispose(){
        super.dispose();

        if (this.mapSceneManager != null){
            this.mapSceneManager.dispose();
        }

        this.mapSceneManager = null;
    }
}

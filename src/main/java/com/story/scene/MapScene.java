package com.story.scene;

import com.story.dataAccessLayer.dataActions.RetrieveMapsAction;
import com.story.scene.components.MapComponent;
import com.story.utils.customException.SceneException;
import com.story.utils.events.Event;
import com.story.utils.events.EventType;
import com.story.utils.log.Trace;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 14.07.16.
 * Represent screen as map where player can moved and execute something actions
 */
class MapScene extends Scene {
    private static final String MapChangeEventName = "MapChange";

    private MapComponent mapComponent;

    /**
     * Initialize new instance of MapScene
     */
    MapScene(){
        this.initialize();
    }

    /**
     * Initialize component
     */
    private void initialize(){
        this.registerEvents();
    }

    /**
     * Create component which should be in scene
     */
    private void loadComponents(){
        if (this.mapComponent != null){
            this.mapComponent.dispose();
        }

        RetrieveMapsAction action = new RetrieveMapsAction();
        this.mapComponent = new MapComponent(action.RetrieveObjectById(1));
        this.mapComponent.addEventListener(EventType.MapRecreate, MapChangeEventName, this::onMapChange);
    }

    /**
     * Initialize the events
     */
    private void registerEvents(){
        this.eventList.addEvent(EventType.SceneReinit, new Event(EventType.SceneReinit));
        this.eventList.addEvent(EventType.SceneRecreate, new Event(EventType.SceneRecreate));
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
        this.loadComponents();
        try {
            this.mapComponent.init(gameContainer);
        } catch (Exception e) {
            SceneException se = new SceneException(e);
            Trace.error(se.getMessage(), se);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.mapComponent.update(gameContainer, delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.mapComponent.render(gameContainer, graphics);
    }

    @Override
    public void dispose(){
        super.dispose();

        if (this.mapComponent != null){
            this.mapComponent.dispose();
        }

        this.mapComponent = null;
    }
}

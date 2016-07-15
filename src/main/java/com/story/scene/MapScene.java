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

import java.util.ArrayList;

/**
 * Created by alex on 14.07.16.
 * Represent screen as map where player can be moved and execute something actions
 */
class MapScene extends Scene {
    private static final String MapChangeEventName = "MapChange";

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
        this.addComponents();
        this.addEvents();
        this.addSubscribe();
    }

    /**
     * Create component which should be in scene
     */
    private void addComponents(){
        this.sceneComponents = new ArrayList<>();
        RetrieveMapsAction action = new RetrieveMapsAction();
        this.sceneComponents.add(new MapComponent(action.RetrieveObjectById(1)));
    }

    /**
     * Initialize the events
     */
    private void addEvents(){
        this.eventList.addEvent(EventType.SceneChanged, new Event(EventType.SceneChanged));
    }

    /**
     * Initialize subscribe
     */
    private void addSubscribe(){
        this.sceneComponents.forEach(component -> {
            component.addEventListener(EventType.MapChange, MapChangeEventName, this::onMapChange);
        });
    }

    /**
     * Method, which called when map change event happened
     */
    private Void onMapChange(Void v){
        System.out.println("Called onMapChange");
        this.eventList.get(EventType.SceneChanged).notifySubscribers();
        return null;
    }

    @Override
    public void init() throws SlickException {
        this.sceneComponents.forEach(component -> {
            try {
                component.init();
            } catch (Exception e) {
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

package com.story.scene;

import com.story.system.IDisposable;
import com.story.utils.events.EventList;
import com.story.utils.events.EventType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.util.function.Function;

/**
 * Created by alex on 14.07.16.
 * Base class of scene type
 */
public abstract class Scene implements IDisposable {
    /**
     * The list of event
     */
    protected EventList eventList;

    /**
     * Initialize new instance of Scene
     */
    public Scene(){
        this.eventList = new EventList();
    }

    /**
     * Add an event listener, if component contains event of selected type
     * @param type the type of event
     * @param eventName the event name
     * @param eventHandler the event handler
     */
    public void addEventListener(EventType type, String eventName, Function<Void, Void> eventHandler){
        if (this.eventList.contains(type)){
            this.eventList.get(type).addSubscriber(eventName, eventHandler);
        }
    }

    /**
     * Remove an event listener, if component contains event of selected type
     * @param type the type of event
     * @param eventName the event name
     */
    public void removeEventListener(EventType type, String eventName){
        if (this.eventList.contains(type)){
            this.eventList.get(type).removeSubscriber(eventName);
        }
    }

    /**
     * Initialize the component
     */
    public abstract void init(GameContainer gameContainer) throws SlickException;

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
        if (eventList.size() > 0){
            this.eventList.dispose();
        }

        this.eventList = null;
    }
}

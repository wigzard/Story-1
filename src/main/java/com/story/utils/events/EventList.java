package com.story.utils.events;

import com.story.system.IDisposable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 14.07.16.
 * Represent collection of event
 */
public class EventList implements IDisposable {

    private HashMap<EventType, Event> events;

    /**
     * Initialize new instance of EventList
     */
    public EventList(){
        this.events = new HashMap<>();
    }

    /**
     * Add event to collection
     * @param type the type of event
     * @param event the event
     */
    public void addEvent(EventType type, Event event){
        if (this.events.containsKey(type)){
            return;
        }

        this.events.put(type, event);
    }

    public boolean contains(EventType type){
        return this.events.containsKey(type);
    }

    public Event get(EventType type){
        return this.events.get(type);
    }

    /**
     * Remove from collection event by type
     * @param type the event type
     */
    public void removeEvent(EventType type){
        if (this.events.containsKey(type)){
            this.events.get(type).dispose();
            this.events.remove(type);
        }
    }

    @Override
    public void dispose() {
        for (Map.Entry<EventType, Event> entry: this.events.entrySet()) {
            entry.getValue().dispose();
        }

        this.events = null;
    }
}

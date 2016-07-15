package com.story.utils.events;

import com.story.system.IDisposable;
import com.story.utils.GlobalHelper;
import com.story.utils.observer.Observer;

import java.util.function.Function;

/**
 * Created by alex on 14.07.16.
 * Represent the event
 */
public class Event implements IDisposable{
    private Observer observableCollection;

    private EventType type;

    /**
     * Initialize new instance of Event
     */
    public Event(EventType type){
        this.type = type;
    }

    /**
     * Add subscriber to observable collection
     * @param name name of callback
     * @param callback the callback
     */
    public void addSubscriber(String name, Function<Void, Void> callback){
        if (this.observableCollection == null){
            this.observableCollection = new Observer();
        }

        if (GlobalHelper.isNullOrEmpty(name) || (callback == null)){
            return;
        }

        this.observableCollection.addSubscriber(name, callback);
    }

    /**
     * Remove subscriber from observable collection
     * @param name the name of subscriber
     */
    public void removeSubscriber(String name){
        if (this.observableCollection == null){
            return;
        }

        this.observableCollection.removeSubscriber(name);
    }

    /**
     * Notify subscribers
     */
    public void notifySubscribers(){
        if (this.observableCollection == null){
            return;
        }

        this.observableCollection.notifySubscribers();
    }

    public EventType getType() {
        return type;
    }

    @Override
    public void dispose() {
        if (this.observableCollection != null){
            this.observableCollection.dispose();
        }

        this.observableCollection = null;
    }
}

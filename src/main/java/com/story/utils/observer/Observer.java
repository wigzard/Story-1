package com.story.utils.observer;

import com.story.system.IDisposable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by alex on 14.07.16.
 * Implements logic of observer pattern
 */
public class Observer implements IDisposable {
    /**
     * Collection of callbacks
     */
    private HashMap<String, Function<Void, Void>> collection;

    /**
     * Initialize instance of Observer
     */
    public Observer(){
    }

    /**
     * Add subscriber to collection
     * @param name unique name of callback
     * @param callback the callback object
     */
    public void addSubscriber(String name, Function<Void, Void> callback){
        if (this.collection == null){
            this.collection = new HashMap<>();
        }

        this.collection.put(name, callback);
    }

    /**
     * Remove subscriber from collection
     * @param name the name of subscriber
     */
    public void removeSubscriber(String name){
        if (this.collection == null){
            return;
        }

        if (this.collection.containsKey(name)){
            this.collection.remove(name);
        }
    }

    /**
     * Notify all subscribers
     */
    public void notifySubscribers(){
        if ((this.collection == null) || (this.collection.size() == 0)){
            return;
        }

        for (Map.Entry<String, Function<Void, Void>> entry : this.collection.entrySet()){
            entry.getValue().apply(null);
        }
    }

    @Override
    public void dispose() {
        this.collection = null;
    }
}

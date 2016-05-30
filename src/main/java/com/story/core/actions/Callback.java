package com.story.core.actions;

/**
 * Created by alex on 27.05.16.
 */
public abstract class Callback<T, V> {
    public abstract T call(V args);
}

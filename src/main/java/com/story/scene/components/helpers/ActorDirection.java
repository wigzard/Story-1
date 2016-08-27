package com.story.scene.components.helpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by alex on 23.07.16.
 */
public enum ActorDirection {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    private static final List<ActorDirection> values = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int size = values.size();
    private static final Random random = new Random();

    public static ActorDirection random()  {
        return values.get(random.nextInt(size));
    }
}

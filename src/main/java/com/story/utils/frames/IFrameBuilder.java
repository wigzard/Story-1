package com.story.utils.frames;

import com.story.core.entities.map.MapEntity;

import java.awt.*;
import java.util.Queue;

/**
 * Created by alex on 28.04.16.
 */
public interface IFrameBuilder {
    Queue<com.story.utils.frames.Frame> buildFrames(MapEntity map, Point start, Point end);
}

package com.story.core.frames;

import com.story.core.entities.map.MapEntity;

import java.awt.*;
import java.util.Queue;

/**
 * Created by alex on 28.04.16.
 */
public interface IFrameBuilder {
    Queue<Frame> buildFrames(MapEntity map, Point start, Point end);
}

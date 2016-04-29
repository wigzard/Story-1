package com.story.core.frames;

import java.awt.*;
import java.util.Queue;

/**
 * Created by alex on 28.04.16.
 */
public interface IFrameBuilder {
    Queue<CentralObject> buildFrames(Point start, Point end);
}

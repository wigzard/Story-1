package com.story.game.callbacks.arguments;

import com.story.core.actions.CallbackArgs;

import java.awt.*;

/**
 * Created by alex on 27.05.16.
 */
public class MoveArgs extends CallbackArgs {
    private Point start;
    private Point end;

    public MoveArgs(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }
}

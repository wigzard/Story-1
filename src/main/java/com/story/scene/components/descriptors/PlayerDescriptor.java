package com.story.scene.components.descriptors;

import com.story.dataAccessLayer.dataActions.RetrieveActorAction;
import com.story.dataAccessLayer.dataDescriptors.ActorDescriptor;
import com.story.system.IDisposable;
import com.story.utils.Size;

import java.awt.*;

/**
 * Created by alex on 23.07.16.
 */
public class PlayerDescriptor implements IDisposable {
    private Point centerPosition;
    private Size tileSize;
    private Point startPosition;

    /**
     * The data from database
     */
    private ActorDescriptor actorDescriptor;

    public PlayerDescriptor(int playerId) {
        this.actorDescriptor = new RetrieveActorAction().retrievePersonById(playerId);
    }

    public Point getCenterPosition() {
        return centerPosition;
    }

    public Size getTileSize() {
        return tileSize;
    }

    public void setCentralPoint(Point centerPosition){
        this.centerPosition = centerPosition;
    }

    public void setTileSize(Size tileSize){
        this.tileSize = tileSize;
    }

    public Point getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Point startPosition) {
        this.startPosition = startPosition;
    }

    public int getId(){
        return this.actorDescriptor.getId();
    }

    public String getName(){
        return this.actorDescriptor.getName();
    }

    public String getSpriteSheetPath(){
        return this.actorDescriptor.getSpriteSheetPath();
    }

    @Override
    public void dispose() {
        if (this.actorDescriptor != null){
            this.actorDescriptor.dispose();
        }

        this.centerPosition = null;
        this.tileSize = null;
        this.actorDescriptor = null;
    }
}

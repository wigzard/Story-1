package com.story.scene.managers;

import com.story.dataAccessLayer.dataActions.RetrieveMapsAction;
import com.story.scene.components.MapComponent;
import com.story.scene.components.PlayerComponent;
import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.scene.components.helpers.ComponentAction;
import com.story.scene.sceneDescriptors.MapSceneDescriptor;
import com.story.system.IDisposable;
import com.story.utils.Size;
import com.story.utils.customException.SceneException;
import com.story.utils.log.Trace;

import java.awt.*;

/**
 * Created by alex on 01.08.16.
 * Manage the map
 */
public class MapSceneManager implements IDisposable{
    /**
     * Component which represent the map
     */
    private MapComponent mapComponent;

    /**
     * Component which represent the player component
     */
    private PlayerComponent playerComponent;

    /**
     * The descriptor, which describe the scene
     */
    private MapSceneDescriptor sceneDescriptor;

    public MapSceneManager(MapSceneDescriptor descriptor){
        if (descriptor == null){
            throw new IllegalArgumentException("Scene descriptor shouldn't been null");
        }

        this.sceneDescriptor = descriptor;
    }

    /**
     * Create the player component
     * @return The player component
     */
    private PlayerComponent createPlayerComponent(int playerId,
            Point startPosition,
            Size tileSize,
            Point centralPoint){
        if (this.playerComponent != null){
            this.playerComponent.dispose();
        }

        PlayerDescriptor playerDescriptor = new PlayerDescriptor(playerId);
        playerDescriptor.setTileSize(tileSize);
        playerDescriptor.setCentralPoint(centralPoint);
        playerDescriptor.setStartPosition(startPosition);
        return new PlayerComponent(playerDescriptor);
    }

    /**
     * Creates the map component
     * @param mapId the map id, what happen in database
     * @param startPosition the start position for map
     * @return the map component
     */
    private MapComponent createMapComponent(int mapId, Point startPosition){
        if (this.mapComponent != null){
            this.mapComponent.dispose();
        }

        RetrieveMapsAction action = new RetrieveMapsAction();
        return new MapComponent(action.retrieveObjectById(mapId), startPosition);
    }

    /**
     * Method checks when player component can be moved by the map
     * @param point the point for move
     * @return true, when player can be moved to point
     */
    private boolean playerCanMove(Point point){
        return (this.mapComponent.isVisibleOnViewer(point))
                && (this.mapComponent.isFreeSpace(point))
                && (this.mapComponent.isFinishFramesDrawing());
    }

    /**
     * Move the player component to new place
     * @param newPoint the new place on map
     */
    private void movePlayer(Point newPoint, ComponentAction action){
        if (this.playerCanMove(newPoint)){
            this.playerComponent.moveTo(newPoint);
            this.getMapComponent().executeAction(action);
        }
    }

    /**
     * @return the map component
     */
    public MapComponent getMapComponent() {
        if (this.mapComponent == null){
            this.mapComponent = this.createMapComponent(this.sceneDescriptor.mapId, this.sceneDescriptor.playerStartPoint);
        }

        return mapComponent;
    }

    /**
     * Create and return the player component
     * @return the player component
     */
    public PlayerComponent getPlayerComponent() {
        if (this.mapComponent == null){
            try {
                throw new SceneException("The map should be initialized before loading player");
            } catch (SceneException e) {
                Trace.error(e.getMessage(), e);
                return null;
            }
        }

        if (this.playerComponent == null){
            this.playerComponent = this.createPlayerComponent(
                    this.sceneDescriptor.playerId,
                    this.sceneDescriptor.playerStartPoint,
                    new Size(32, 32),
                    this.mapComponent.getCentralCoordinate());
        }

        return playerComponent;
    }

    /**
     * The player component should be moved to other ways
     */
    public void executeKeyAction(ComponentAction action){
        switch (action){
            case MOVE_DOWN:
                Point moveDownPoint = new Point(this.playerComponent.getCurrentCoordinate().x, this.playerComponent.getCurrentCoordinate().y + 1);
                this.movePlayer(moveDownPoint, action);
                break;
            case MOVE_UP:
                Point moveUpPoint = new Point(this.playerComponent.getCurrentCoordinate().x, this.playerComponent.getCurrentCoordinate().y - 1);
                this.movePlayer(moveUpPoint, action);
                break;
            case MOVE_LEFT:
                Point moveLeftPoint = new Point(this.playerComponent.getCurrentCoordinate().x - 1, this.playerComponent.getCurrentCoordinate().y);
                this.movePlayer(moveLeftPoint, action);
                break;
            case MOVE_RIGHT:
                Point moveRightPoint = new Point(this.playerComponent.getCurrentCoordinate().x + 1, this.playerComponent.getCurrentCoordinate().y);
                this.movePlayer(moveRightPoint, action);
                break;
        }
    }

    @Override
    public void dispose() {
        if (this.mapComponent != null){
            this.mapComponent.dispose();
        }

        if (this.playerComponent != null){
            this.playerComponent.dispose();
        }

        if (this.sceneDescriptor != null){
            this.sceneDescriptor.dispose();
        }

        this.sceneDescriptor = null;
        this.mapComponent = null;
        this.playerComponent = null;
    }
}

package com.story.scene.managers;

import com.story.dataAccessLayer.dataActions.RetrieveMapsAction;
import com.story.scene.components.MapComponent;
import com.story.scene.components.PlayerComponent;
import com.story.scene.components.SimpleNpcComponent;
import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.scene.components.descriptors.SimpleNpcDescriptor;
import com.story.scene.components.helpers.ComponentAction;
import com.story.scene.sceneDescriptors.MapSceneDescriptor;
import com.story.system.IDisposable;
import com.story.utils.Converter;
import com.story.utils.Size;
import com.story.utils.customException.SceneException;
import com.story.utils.events.EventType;
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
     * List of npc on map
     */
    private SimpleNpcComponent[] npcList;

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
            Point centralPoint){
        if (this.playerComponent != null){
            this.playerComponent.dispose();
        }

        PlayerDescriptor playerDescriptor = new PlayerDescriptor(playerId);
        playerDescriptor.setCentralPoint(centralPoint);
        playerDescriptor.setStartPosition(startPosition);

        PlayerComponent playerComponent = new PlayerComponent(playerDescriptor);
        this.mapComponent.addEventListener(EventType.MapMoveStart,
                PlayerComponent.PlayerMoveStartPropertyName,
                playerComponent::onStartMoveAnimation);

        this.mapComponent.addEventListener(EventType.MapMoveStop,
                PlayerComponent.PlayerMoveStopPropertyName,
                playerComponent::onStopMoveAnimation);

        return playerComponent;
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
     * Creates new list of npc
     * @param descriptors the descriptors of npc list
     * @return list of npc
     */
    private SimpleNpcComponent[] createNpcList(SimpleNpcDescriptor[] descriptors){
        if ((descriptors == null) || (descriptors.length == 0)){
            return null;
        }

        SimpleNpcComponent[] components = new SimpleNpcComponent[descriptors.length];
        for (int i = 0; i < descriptors.length; i++){
            components[i] = new SimpleNpcComponent(descriptors[i]);
        }

        return components;
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
     * Move the components to new place
     * @param newPoint the new place on map
     */
    private void moveComponents(Point newPoint, ComponentAction action){
        this.playerComponent.setDirection(Converter.toActorDirection(action, this.playerComponent.getDirection()));
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
                    this.mapComponent.getCentralCoordinate());
        }

        return playerComponent;
    }

    public SimpleNpcComponent[] getSimpleNpcList(){
        if (this.mapComponent == null){
            try {
                throw new SceneException("The map should be initialized before loading npc");
            } catch (SceneException e) {
                Trace.error(e.getMessage(), e);
                return null;
            }
        }

        if ((this.npcList == null) || (this.npcList.length == 0)){
            this.npcList = this.createNpcList(this.sceneDescriptor.npcDescriptors);
        }

        return this.npcList;
    }

    /**
     * The player component should be moved to other ways
     */
    public void executeKeyAction(ComponentAction action){
        switch (action){
            case MOVE_DOWN:
                Point moveDownPoint = new Point(this.playerComponent.getCurrentCoordinate().x, this.playerComponent.getCurrentCoordinate().y + 1);
                this.moveComponents(moveDownPoint, action);
                break;
            case MOVE_UP:
                Point moveUpPoint = new Point(this.playerComponent.getCurrentCoordinate().x, this.playerComponent.getCurrentCoordinate().y - 1);
                this.moveComponents(moveUpPoint, action);
                break;
            case MOVE_LEFT:
                Point moveLeftPoint = new Point(this.playerComponent.getCurrentCoordinate().x - 1, this.playerComponent.getCurrentCoordinate().y);
                this.moveComponents(moveLeftPoint, action);
                break;
            case MOVE_RIGHT:
                Point moveRightPoint = new Point(this.playerComponent.getCurrentCoordinate().x + 1, this.playerComponent.getCurrentCoordinate().y);
                this.moveComponents(moveRightPoint, action);
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

        if ((this.npcList != null) && (this.npcList.length > 0)){
            for (SimpleNpcComponent component: this.npcList) {
                component.dispose();
            }
        }

        this.sceneDescriptor = null;
        this.mapComponent = null;
        this.playerComponent = null;
        this.npcList = null;
    }
}

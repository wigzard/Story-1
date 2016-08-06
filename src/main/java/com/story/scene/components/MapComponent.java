package com.story.scene.components;

import com.story.dataAccessLayer.dataDescriptors.MapDescriptor;
import com.story.scene.components.helpers.ComponentAction;
import com.story.scene.components.managers.TiledMapManager;
import com.story.utils.GlobalHelper;
import com.story.utils.Size;
import com.story.utils.customException.InvalidDescriptor;
import com.story.utils.events.Event;
import com.story.utils.events.EventType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.io.File;

/**
 * Created by alex on 14.07.16.
 * Represent component which should work with map
 */
public class MapComponent extends Component {

    /**
     * Represent the move state of map component
     */
    private enum MoveState{Moved, Stoped};

    private MapDescriptor mapDescriptor;
    private TiledMapManager mapManager;
    private Point startPosition;

    /**
     * Current move state of the map component
     */
    private MoveState moveState = MoveState.Stoped;

    /**
     * Initialize new instance of MapComponent
     * @param descriptor object, which stored data of map
     */
    public MapComponent(MapDescriptor descriptor, Point startPosition){
        super();
        this.mapDescriptor = descriptor;
        this.startPosition = startPosition;
        this.registerEvents();
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException, InvalidDescriptor {
        this.validateDescriptor();
        if (this.mapManager != null){
            this.mapManager.dispose();
        }
        this.mapManager = new TiledMapManager(new TiledMap(this.mapDescriptor.getPathToTMX()),
                new Size(gameContainer.getWidth(), gameContainer.getHeight()),
                this.startPosition);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        if ((this.moveState == MoveState.Stoped) && (this.mapManager.getCountFramesInViewer() > 0)){
            this.moveState = MoveState.Moved;
            this.eventList.get(EventType.MapMoveStart).notifySubscribers();
        }
        else if ((this.moveState == MoveState.Moved) && (this.mapManager.getCountFramesInViewer() == 0)){
            this.moveState = MoveState.Stoped;
            this.eventList.get(EventType.MapMoveStop).notifySubscribers();
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.mapManager.getMap().render(this.mapManager.getCurrentCoordinate().x,
                this.mapManager.getCurrentCoordinate().y);
    }

    /**
     * Initiate the action by type {@link ComponentAction}
     * @param action the type of action
     */
    public void executeAction(ComponentAction action){
        switch (action){
            case MOVE_DOWN:
                this.mapManager.moveDown();
                break;
            case MOVE_UP:
                this.mapManager.moveUp();
                break;
            case MOVE_LEFT:
                this.mapManager.moveLeft();
                break;
            case MOVE_RIGHT:
                this.mapManager.moveRight();
                break;
        }
    }

    /**
     * Check when coordinates of object with coordinates @coordinates, is visible in the viewer
     * @param coordinates the object coordinates on tile map
     * @return true, when coordinates is displayed on viewer
     */
    public boolean isVisibleOnViewer(Point coordinates){
        return this.mapManager.isVisibleOnViewer(coordinates);
    }

    /**
     * Check when all frames were been draw
     * @return true, when count of frames for draw is 0
     */
    public boolean isFinishFramesDrawing(){
        return this.mapManager.getCountFramesInViewer() == 0;
    }

    /**
     * Check when object with @coordinates can be moved to tile with this @coordinates
     * @param coordinates the object coordinates
     * @return true when object with @coordinates can be set on this place
     */
    public boolean isFreeSpace(Point coordinates){
        return this.mapManager.isFreeSpace(coordinates);
    }

    /**
     * Gets coordinates of center tile
     * @return the point of center
     */
    public Point getCentralCoordinate(){
        return this.mapManager.getCenterTileAsGlobal();
    }

    /**
     * Check fields of descriptor's map
     * @throws InvalidDescriptor indicated when descriptor is not correct
     */
    private void validateDescriptor() throws InvalidDescriptor {
        if (this.mapDescriptor == null){
            throw new InvalidDescriptor("The map descriptor doesn't set");
        }

        if (GlobalHelper.isNullOrEmpty(this.mapDescriptor.getPathToTMX())){
            throw new InvalidDescriptor("The path to tmx file is null from mapDescriptor");
        }

        if (!new File(this.mapDescriptor.getPathToTMX()).exists()){
            throw new InvalidDescriptor("The file of tmx doesn't exists");
        }
    }

    /**
     * Initialize the events
     */
    private void registerEvents(){
        this.eventList.addEvent(EventType.MapRecreate, new Event(EventType.MapRecreate));
        this.eventList.addEvent(EventType.MapMoveStart, new Event(EventType.MapMoveStart));
        this.eventList.addEvent(EventType.MapMoveStop, new Event(EventType.MapMoveStop));
    }

    /**
     * Disposing instance
     */
    @Override
    public void dispose() {
        super.dispose();

        if (this.mapDescriptor != null){
            this.mapDescriptor.dispose();
        }

        if (this.mapManager != null){
            this.mapManager.dispose();
        }

        this.mapDescriptor = null;
        this.mapManager = null;
    }
}

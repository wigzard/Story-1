package com.story.scene;

import com.story.dataAccessLayer.dataActions.RetrieveMapsAction;
import com.story.scene.components.MapComponent;
import com.story.scene.components.PlayerComponent;
import com.story.scene.components.descriptors.PlayerDescriptor;
import com.story.utils.customException.SceneException;
import com.story.utils.events.Event;
import com.story.utils.events.EventType;
import com.story.utils.log.Trace;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 14.07.16.
 * Represent screen as map where player can moved and execute something actions
 */
class MapScene extends Scene {
    private static final String MapChangeEventName = "MapChange";

    private Point startPosition;

    /**
     * Component which represent the map
     */
    private MapComponent mapComponent;

    /**
     * Component which represent the player component
     */
    private PlayerComponent playerComponent;

    /**
     * Initialize new instance of MapScene
     */
    MapScene(){
        this.initialize();
    }

    /**
     * Initialize component
     */
    private void initialize(){
        this.registerEvents();
        this.startPosition = new Point(12, 12);
    }

    /**
     * Create the map component
     */
    private void loadMapComponent(){
        if (this.mapComponent != null){
            this.mapComponent.dispose();
        }

        RetrieveMapsAction action = new RetrieveMapsAction();
        this.mapComponent = new MapComponent(action.RetrieveObjectById(1), this.startPosition);
        this.mapComponent.addEventListener(EventType.MapRecreate, MapChangeEventName, this::onMapChange);
    }

    /**
     * Loading the player component
     */
    private void loadPlayerComponent(){
        PlayerDescriptor playerDescriptor = new PlayerDescriptor();
        playerDescriptor.setTileSize(this.mapComponent.getTileSize());
        playerDescriptor.setCentralPoint(this.mapComponent.getCentralCoordinate());
        playerDescriptor.setUrl("resources/player_picture_set.png");
        playerDescriptor.setStartPosition(this.startPosition);
        this.playerComponent = new PlayerComponent(playerDescriptor);
    }

    /**
     * Initialize the events
     */
    private void registerEvents(){
        this.eventList.addEvent(EventType.SceneReinit, new Event(EventType.SceneReinit));
        this.eventList.addEvent(EventType.SceneRecreate, new Event(EventType.SceneRecreate));
    }

    /**
     * Method, which called when map change event happened
     */
    private Void onMapChange(Void v){
        System.out.println("Called onMapRecreate");
        this.eventList.get(EventType.SceneReinit).notifySubscribers();
        return null;
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

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            this.loadMapComponent();
            this.mapComponent.init(gameContainer);

            this.loadPlayerComponent();
            this.playerComponent.init(gameContainer);
        } catch (Exception e) {
            SceneException se = new SceneException(e);
            Trace.error(se.getMessage(), se);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        Point newPoint = new Point(this.playerComponent.getCurrentCoordinate());
        boolean shouldBeMoved = false;
        if (gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)){
            newPoint.x++;
            shouldBeMoved = true;
        }
        else if (gameContainer.getInput().isKeyDown(Input.KEY_LEFT)){
            newPoint.x--;
            shouldBeMoved = true;
        }
        else if (gameContainer.getInput().isKeyDown(Input.KEY_UP)){
            newPoint.y--;
            shouldBeMoved = true;
        }
        else if (gameContainer.getInput().isKeyDown(Input.KEY_DOWN)){
            newPoint.y++;
            shouldBeMoved = true;
        }

        if ((shouldBeMoved) && (this.playerCanMove(newPoint))){
            this.playerComponent.moveTo(newPoint);
            this.mapComponent.update(gameContainer, delta);
        }

        this.playerComponent.update(gameContainer, delta);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.mapComponent.render(gameContainer, graphics);
        this.playerComponent.render(gameContainer, graphics);
    }

    @Override
    public void dispose(){
        super.dispose();

        if (this.mapComponent != null){
            this.mapComponent.dispose();
        }

        if (this.playerComponent != null){
            this.playerComponent.dispose();
        }

        this.mapComponent = null;
        this.playerComponent = null;
    }
}

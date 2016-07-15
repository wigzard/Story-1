package com.story.scene.components;

import com.story.dataAccessLayer.dataDescriptors.MapDescriptor;
import com.story.utils.GlobalHelper;
import com.story.utils.customException.InvalidDescriptor;
import com.story.utils.events.Event;
import com.story.utils.events.EventType;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.io.File;

/**
 * Created by alex on 14.07.16.
 * Represent component which should work with map
 */
public class MapComponent extends Component {
    private MapDescriptor mapDescriptor;
    private TiledMap map;

    /**
     * Initialize new instance of MapComponent
     * @param descriptor object, which stored data of map
     */
    public MapComponent(MapDescriptor descriptor){
        super();
        this.mapDescriptor = descriptor;

        this.initializeEvents();
    }

    @Override
    public void init() throws SlickException, InvalidDescriptor {
        this.validateDescriptor();
        this.map = new TiledMap(this.mapDescriptor.getPathToTMX());
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_RIGHT)){
            this.eventList.get(EventType.MapChange).notifySubscribers();
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        this.map.render(0, 0);
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
    private void initializeEvents(){
        this.eventList.addEvent(EventType.MapChange, new Event(EventType.MapChange));
    }

    /**
     * Disposing instance
     */
    @Override
    public void dispose() {
        if (this.mapDescriptor != null){
            this.mapDescriptor.dispose();
        }

        this.mapDescriptor = null;
    }
}

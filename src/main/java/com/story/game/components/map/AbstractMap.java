package com.story.game.components.map;

import com.story.core.actions.Callback;
import com.story.core.actions.CallbackArgs;
import com.story.core.entities.Npc;
import com.story.core.entities.Player;
import com.story.core.entities.map.MapEntity;
import com.story.game.callbacks.arguments.MoveArgs;
import com.story.game.storages.GlobalStorage;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.global.ActionType;
import com.story.utils.frames.Frame;
import com.story.utils.frames.IFrameStorage;

import java.awt.*;
import java.util.List;
import java.util.Queue;

/**
 * Created by alex on 23.05.16.
 */
public abstract class AbstractMap extends MapEntity{
    private MapElements mapComponents;

    public AbstractMap(MapDescriptor map) {
        super(map);
        this.mapComponents = new MapElements();
    }

    protected abstract Queue<Frame> buildPlayerFrames(Point start, Point end);

    /**
     * Get the player component
     * @return player component
     */
    public Player getPlayerComponent(){
        return this.mapComponents.getPlayerComponent();
    }

    /**
     * Set the player component and was adding event listener
     * @param component The player component
     */
    public void setPlayerComponent(Player component){
        if (component == null){
            return;
        }
        this.mapComponents.setPlayerComponent(component);
        this.initPlayerEventListeners();
    }

    /**
     * Add the simple npc object to map entities
     * @param component
     */
    public void addSimpleNpc(Npc component){
        this.mapComponents.addSimpleNPC(component);
    }

    /**
     * Add the simple npc components
     * @param components
     */
    public void addSimpleNpc(List<Npc> components){
        this.mapComponents.addSimpleNPC(components);
    }

    /**
     * Get the simple npc list
     * @return The simple npc list
     */
    public List<Npc> getNpcList(){
        return this.mapComponents.getSimpleNPC();
    }

    /**
     * Initialize the event listeners for player
     */
    private void initPlayerEventListeners(){
        this.getPlayerComponent().addEventListener(ActionType.MOVE,
                new Callback<Boolean, CallbackArgs>() {
                    @Override
                    public Boolean call(CallbackArgs args) {
                        try {
                            MoveArgs params;
                            if (args instanceof MoveArgs){
                                params = (MoveArgs)args;
                            }
                            else {
                                return false;
                            }

                            IFrameStorage frameStorage = GlobalStorage.getInstance().getScope().getCentralFrameStorage();

                            //wait while all frames will be drawer
                            if (frameStorage.hasNextFrame()){
                                return false;
                            }

                            Queue<Frame> frames = buildPlayerFrames(params.getStart(), params.getEnd());
                            if ((frames == null) || (frames.size() == 0)){
                                return false;
                            }

                            frameStorage.addFrames(frames);
                            return true;
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            return false;
                        }
                    }
                });
    }
}

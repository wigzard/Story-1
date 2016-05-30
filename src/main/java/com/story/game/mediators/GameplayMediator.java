package com.story.game.mediators;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.game.handlers.NpcMoveHandler;
import com.story.game.handlers.eventArgs.GameEventArgs;
import com.story.game.handlers.IKeyAction;
import com.story.game.handlers.eventArgs.NpcMoveEventArgs;
import com.story.game.scenarion.Scenario;
import com.story.game.storages.GlobalStorage;
import com.story.modules.global.ActionType;
import org.newdawn.slick.Input;

/**
 * Created by alex on 04.05.16.
 */
public class GameplayMediator implements IGameplaymediator{
    private IKeyAction handler;
    private GameEventArgs keyPressArgs;

    private NpcMoveHandler npcMoveHandler;
    private NpcMoveEventArgs npcMoveEventArgs;

    public GameplayMediator(){
        this.keyPressArgs = new GameEventArgs(ActionType.KEY_PRESSED);
        this.npcMoveHandler = new NpcMoveHandler();
    }

    /**
     * Initialize a GlobalStorage object
     * @param descriptorFacade the descriptor facade
     */
    @Override
    public void init(IDescriptorFacade descriptorFacade, Scenario scenario)
            throws LoadSystemObjectException {
        if (descriptorFacade == null){
            throw new ExceptionInInitializerError("The descriptorFacade is null or empty.");
        }

        GlobalStorage.getInstance().init(descriptorFacade, scenario);
        this.npcMoveEventArgs = new NpcMoveEventArgs();
        this.npcMoveEventArgs.listOfNpc = GlobalStorage.getInstance().getScope().getMapComponent().getNpcList();
    }

    @Override
    public void onUpdate(Input input) {
        this.keyPressArgs.setInput(input);
        this.handler.onHandle(this.keyPressArgs);

        this.npcMoveHandler.onHandle(this.npcMoveEventArgs);
    }

    @Override
    public void setActionHandler(IKeyAction handler) {
        this.handler = handler;
    }
}

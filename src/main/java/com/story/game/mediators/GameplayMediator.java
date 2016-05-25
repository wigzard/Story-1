package com.story.game.mediators;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.action.eventArgs.GameEventArgs;
import com.story.game.action.IKeyAction;
import com.story.game.scenarion.Scenario;
import com.story.game.storages.GlobalStorage;
import com.story.modules.global.ActionType;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 04.05.16.
 */
public class GameplayMediator implements IGameplaymediator{
    private IKeyAction handler;
    private GameEventArgs keyPressArgs;

    public GameplayMediator(){
        this.keyPressArgs = new GameEventArgs(ActionType.KEY_PRESSED);
    }

    /**
     * Initialize a GlobalStorage object
     * @param descriptorFacade the descriptor facade
     */
    @Override
    public void init(IDescriptorFacade descriptorFacade,
                     Scenario scenario,
                     IFrameStorage centralFrameStorage,
                     IFrameStorage frameStorage) throws LoadSystemObjectException {
        if (descriptorFacade == null){
            throw new ExceptionInInitializerError("The descriptorFacade is null or empty.");
        }

        GlobalStorage.getInstance().init(descriptorFacade, scenario);
    }

    @Override
    public void ExecuteAction(Input input) {
        this.keyPressArgs.setInput(input);
        this.handler.onHandle(this.keyPressArgs);
    }

    @Override
    public void setActionHandler(IKeyAction handler) {
        this.handler = handler;
    }
}

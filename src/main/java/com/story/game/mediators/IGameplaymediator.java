package com.story.game.mediators;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.utils.frames.IFrameStorage;
import com.story.game.action.IKeyAction;
import com.story.game.scenarion.Scenario;
import org.newdawn.slick.Input;

/**
 * Created by alex on 04.05.16.
 */
public interface IGameplaymediator {
    void ExecuteAction(Input input);
    void setActionHandler(IKeyAction handler);
    void init(IDescriptorFacade descriptorFacade, Scenario scenario) throws LoadSystemObjectException;
}

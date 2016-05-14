package com.story.game.mediators;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.handlers.IActionHandler;
import com.story.game.storages.ProxyScope;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 04.05.16.
 */
public interface IGameplaymediator {
    ProxyScope.ScopeStorage getStorageScope();
    void ExecuteAction(Input input);
    void setActionHandler(IActionHandler handler);
    void setDescriptorFacade(IDescriptorFacade descriptorFacade);
    void loadMap(int mapDescriptorId) throws SlickException;
    void loadPlayer(int playerDescriptorId) throws SlickException;
    void setFrameStorage(IFrameStorage storage);
    void init(IDescriptorFacade descriptorFacade, int mapId, int playerId, IFrameStorage storage) throws LoadSystemObjectException;
}

package com.story.game.mediators;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.action.eventArgs.GameEventArgs;
import com.story.game.action.IKeyAction;
import com.story.game.storages.ProxyScope;
import com.story.modules.global.ActionType;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 04.05.16.
 */
public class GameplayMediator implements IGameplaymediator{
    private ProxyScope proxyScope;
    private IKeyAction handler;
    private GameEventArgs keyPressArgs;

    public GameplayMediator(){
        this.keyPressArgs = new GameEventArgs(ActionType.KEY_PRESSED);
    }

    @Override
    public void setDescriptorFacade(IDescriptorFacade descriptorFacade){
        if (this.proxyScope == null){
            this.proxyScope = new ProxyScope(descriptorFacade);
            return;
        }

        this.proxyScope.setDescriptorFacade(descriptorFacade);
    }

    @Override
    public void setCentralFrameStorage(IFrameStorage storage){
        this.proxyScope.setCentralFrameStorage(storage);
    }

    /**
     * Initialize a ProxyScope object
     * @param descriptorFacade the descriptor facade
     * @param mapId the map descriptor id
     * @param playerId the player descriptor id
     */
    @Override
    public void init(IDescriptorFacade descriptorFacade,
                     int mapId,
                     int playerId,
                     int[] simpleNPCIds,
                     IFrameStorage centralFrameStorage,
                     IFrameStorage frameStorage) throws LoadSystemObjectException {
        if (descriptorFacade == null){
            throw new ExceptionInInitializerError("The descriptorFacade is null or empty.");
        }

        this.setDescriptorFacade(descriptorFacade);
        this.proxyScope.init(mapId, playerId, simpleNPCIds, centralFrameStorage, frameStorage);
        this.keyPressArgs.setSystemObjectScope(this.getStorageScope());
    }

    @Override
    public void loadMap(int mapDescriptorId) throws SlickException {
        this.proxyScope.setMapHandler(mapDescriptorId);
    }

    @Override
    public void loadPlayer(int playerDescriptorId) throws SlickException {
        this.proxyScope.setPlayerDescriptor(playerDescriptorId);
    }

    @Override
    public ProxyScope.ScopeStorage getStorageScope() {
        return this.proxyScope.getCurrentScore();
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

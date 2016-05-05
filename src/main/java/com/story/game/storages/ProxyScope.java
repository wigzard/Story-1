package com.story.game.storages;

import com.story.core.baseHandlers.MapHandler;
import com.story.core.baseHandlers.PlayerHandler;
import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.handlers.BaseMapHandler;
import com.story.game.handlers.BasePlayerHandler;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 04.05.16.
 * Class for control of access to the ScopeStorage instance.
 */
public class ProxyScope {
    private ScopeStorage currentScore;
    private IDescriptorFacade descriptorFacade;
    private boolean isScopeInit;

    public ProxyScope(IDescriptorFacade facade){
        this.setDescriptorFacade(facade);
        this.currentScore = new ScopeStorage();
        this.isScopeInit = false;
    }

    /**
     * Set the descriptor facade. Many descriptors are generated with help of this facade
     * @param facade new facade
     */
    public void setDescriptorFacade(IDescriptorFacade facade){
        this.descriptorFacade = facade;
    }

    /**
     * Change the map descriptor and create new a map handler/
     * @param mapDescriptorId descriptor id
     * @throws SlickException
     */
    public void setMapHandler(int mapDescriptorId) throws SlickException {
        this.currentScore.mapHandler = new BaseMapHandler(this.descriptorFacade.getMap(mapDescriptorId));
    }

    /**
     * Change the player descriptor and create new a player handler
     * @param playerDescriptorId descriptor id
     * @throws SlickException
     */
    public void setPlayerDescriptor(int playerDescriptorId) throws SlickException {
        this.currentScore.playerHandler = new BasePlayerHandler(
                this.descriptorFacade.getPlayer(playerDescriptorId));
    }

    /**
     * Create new the storage
     * @param storage new storage
     */
    private void setFrameStorage(IFrameStorage storage){
        this.currentScore.frameStorage = storage;
    }

    /**
     *
     * @return the currentScope object if the proxy object is initialized.
     */
    public ScopeStorage getCurrentScore() {
        if (!this.isScopeInit){
            throw new ExceptionInInitializerError("The ProxyScope was does't initialized.");
        }

        return currentScore;
    }

    /**
     * Init all the scope storage objects
     * @throws LoadSystemObjectException
     */
    public void init(int mapId, int playerId, IFrameStorage storage) throws LoadSystemObjectException {
        try {
            this.setMapHandler(mapId);
            this.setPlayerDescriptor(playerId);
            this.setFrameStorage(storage);
            this.isScopeInit = true;
        }
        catch (Exception e){
            LoadSystemObjectException customException = new LoadSystemObjectException("Can't load the system object");
            customException.setStackTrace(e.getStackTrace());
            throw customException;
        }
    }

    public class ScopeStorage{
        private MapHandler mapHandler;
        private PlayerHandler playerHandler;
        private IFrameStorage frameStorage;

        public MapHandler getMapHandler() {
            return mapHandler;
        }

        public PlayerHandler getPlayerHandler() {
            return playerHandler;
        }

        public IFrameStorage getFrameStorage() {
            return frameStorage;
        }
    }
}

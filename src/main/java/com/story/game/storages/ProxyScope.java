package com.story.game.storages;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.components.map.AbstractMap;
import com.story.game.factories.ComponentFactory;
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
    public void setMapHandler(int mapDescriptorId, int playerDescriptorId, int[] npcIds) throws SlickException {
        this.currentScore.mapHandler = ComponentFactory.createMapComponent(
                this.descriptorFacade,
                mapDescriptorId,
                playerDescriptorId,
                npcIds);
    }

    /**
     * Create new the storage
     * @param storage new storage
     */
    public void setCentralFrameStorage(IFrameStorage storage){
        this.currentScore.centralFrameStorage = storage;
    }

    public void setFrameStorage(IFrameStorage storage){
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
    public void init(int mapId,
                     int playerId,
                     int[] simpleNPCIds,
                     IFrameStorage centralFrameStorage,
                     IFrameStorage frameStorage) throws LoadSystemObjectException {
        try {
            this.setMapHandler(mapId, playerId, simpleNPCIds);
            this.setCentralFrameStorage(centralFrameStorage);
            this.setFrameStorage(frameStorage);
            this.isScopeInit = true;
        }
        catch (Exception e){
            LoadSystemObjectException customException = new LoadSystemObjectException("Can't load the system object");
            customException.setStackTrace(e.getStackTrace());
            throw customException;
        }
    }

    public class ScopeStorage{
        private AbstractMap mapHandler;
        private IFrameStorage centralFrameStorage;
        private IFrameStorage frameStorage;

        public AbstractMap getMapComponent() {
            return mapHandler;
        }

        public IFrameStorage getCentralFrameStorage() {
            return centralFrameStorage;
        }

        public IFrameStorage getFrameStorage() {
            return frameStorage;
        }
    }
}

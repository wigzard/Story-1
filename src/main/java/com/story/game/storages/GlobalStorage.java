package com.story.game.storages;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.components.map.AbstractMap;
import com.story.game.factories.ComponentFactory;
import com.story.game.scenarion.Scenario;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 04.05.16.
 * Class for control of access to the ScopeStorage instance.
 */
public class GlobalStorage {
    private static GlobalStorage instance;

    private ScopeStorage currentScore;
    private IDescriptorFacade descriptorFacade;
    private boolean isScopeInit;

    private GlobalStorage(){
        this.currentScore = new ScopeStorage();
        this.currentScore.centralFrameStorage = new QueueFrameStorage();
        this.currentScore.frameStorage = new QueueFrameStorage();
        this.isScopeInit = false;
    }

    /**
     * Get instance of proxy scope
     */
    public static GlobalStorage getInstance(){
        if (instance == null){
            instance = new GlobalStorage();
        }

        return instance;
    }

    public static void initialize(IDescriptorFacade facade, Scenario scenario) throws LoadSystemObjectException {
        getInstance().init(facade, scenario);
    }

    /**
     * @return true if instance is initialize
     */
    public boolean isScopeInit(){
        return this.isScopeInit;
    }

    /**
     * Set the descriptor facade. Many descriptors are generated with help of this facade
     * @param facade new facade
     */
    public void setDescriptorFacade(IDescriptorFacade facade){
        this.descriptorFacade = facade;
    }

    /**
     * Change the map descriptor and create new a map handler
     * @throws SlickException
     */
    public void setMapHandler(Scenario scenario) throws SlickException {
        this.currentScore.mapHandler = ComponentFactory.createMapComponent(
                this.descriptorFacade,
                scenario);
    }
    /**
     *
     * @return the currentScope object if the proxy object is initialized.
     */
    public ScopeStorage getScore() {
        if (!this.isScopeInit){
            throw new ExceptionInInitializerError("The GlobalStorage was does't initialized.");
        }

        return currentScore;
    }

    /**
     * Init all the scope storage objects
     * @throws LoadSystemObjectException
     */
    public void init(IDescriptorFacade facade, Scenario scenario) throws LoadSystemObjectException {
        try {
            if (facade == null) {
                return;
            }
            this.setDescriptorFacade(facade);
            this.setMapHandler(scenario);
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
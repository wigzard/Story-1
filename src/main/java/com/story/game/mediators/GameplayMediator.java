package com.story.game.mediators;

import com.story.core.GlobalVar;
import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.storages.ProxyScope;
import com.story.game.storages.QueueFrameStorage;
import com.story.modules.dbdata.DBFacade;

/**
 * Created by alex on 04.05.16.
 */
public class GameplayMediator implements IGameplaymediator{
    //Hardcode constants
    private int mapDescriptorId = 1;
    private int playerDescriptorId = 1;
    private IFrameStorage storage = new QueueFrameStorage();
    private IDescriptorFacade dbFacade = new DBFacade(GlobalVar.dbName);
    //------------------

    private ProxyScope proxyScope;

    public GameplayMediator(){
        this.proxyScope = new ProxyScope(dbFacade);

        try {
            this.proxyScope.init(this.mapDescriptorId, this.playerDescriptorId, storage);
        } catch (LoadSystemObjectException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProxyScope.ScopeStorage getStorageScope() {
        return proxyScope.getCurrentScore();
    }
}

package com.story.game.mediators;

import com.story.core.customException.LoadSystemObjectException;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.core.frames.IFrameStorage;
import com.story.game.eventArgs.GameEventArgs;
import com.story.game.handlers.action.IActionHandler;
import com.story.game.storages.ProxyScope;
import com.story.game.storages.QueueFrameStorage;
import com.story.modules.dbdata.DBFacade;
import com.story.modules.global.ActionType;
import com.story.modules.global.GlobalVar;
import org.newdawn.slick.Input;

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
    private IActionHandler handler;
    private GameEventArgs keyPressArgs;

    public GameplayMediator(){
        this.proxyScope = new ProxyScope(dbFacade);

        try {
            this.proxyScope.init(this.mapDescriptorId, this.playerDescriptorId, storage);
        } catch (LoadSystemObjectException e) {
            e.printStackTrace();
        }
        this.keyPressArgs = new GameEventArgs(ActionType.KEY_PRESSED);
        this.keyPressArgs.setSystemObjectScope(this.getStorageScope());
    }

    @Override
    public ProxyScope.ScopeStorage getStorageScope() {
        return proxyScope.getCurrentScore();
    }

    @Override
    public void ExecuteAction(Input input) {
        this.keyPressArgs.setInput(input);
        this.handler.onHandle(this.keyPressArgs);
    }

    @Override
    public void setActionHandler(IActionHandler handler) {
        this.handler = handler;
    }
}

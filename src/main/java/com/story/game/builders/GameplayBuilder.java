package com.story.game.builders;

import com.story.core.coreHandlers.PlayerHandler;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.game.handlers.action.IActionHandler;
import com.story.game.handlers.action.KeyEventHandler;
import com.story.game.mediators.GameplayMediator;
import com.story.game.mediators.IGameplaymediator;
import com.story.game.storages.ProxyScope;
import org.newdawn.slick.Input;

/**
 * Created by alex on 07.05.16.
 */
class GameplayBuilder implements IGameplayBuilder {
    private GameplayMediator gameplaymediator;

    @Override
    public void createInstance(){
        this.gameplaymediator = new GameplayMediator();
    }

    @Override
    public void createKeyEvents(IActionHandler handler){
        this.gameplaymediator.setActionHandler(handler);
        initKeysEvent(handler);
    }

    @Override
    public IGameplaymediator getInstance(){
        return this.gameplaymediator;
    }

    private void initKeysEvent(IActionHandler handler){
        handler.addKeyEventListener(Input.KEY_LEFT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.LEFT,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
        handler.addKeyEventListener(Input.KEY_RIGHT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.RIGHT,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
        handler.addKeyEventListener(Input.KEY_UP, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.UP,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
        handler.addKeyEventListener(Input.KEY_DOWN, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.DOWN,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
    }
}

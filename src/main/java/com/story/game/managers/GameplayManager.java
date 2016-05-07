package com.story.game.managers;

import com.story.core.coreHandlers.PlayerHandler;
import com.story.game.handlers.action.ActionHandler;
import com.story.game.handlers.action.KeyEventHandler;
import com.story.game.mediators.GameplayMediator;
import com.story.game.mediators.IGameplaymediator;
import com.story.game.storages.ProxyScope;
import org.newdawn.slick.Input;

import java.awt.*;

/**
 * Created by alex on 07.05.16.
 */
public class GameplayManager {
    //Hardcode constants
    private ActionHandler handler = new ActionHandler();
    //------------------

    private GameplayMediator gameplaymediator;

    public GameplayManager(){
        this.gameplaymediator = new GameplayMediator();
        this.gameplaymediator.setActionHandler(handler);
        initKeysEvent();
    }

    public IGameplaymediator getGameplaymediator(){
        return this.gameplaymediator;
    }

    private void initKeysEvent(){
        this.handler.AddKeyEventListener(Input.KEY_LEFT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.LEFT,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
        this.handler.AddKeyEventListener(Input.KEY_RIGHT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.RIGHT,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
        this.handler.AddKeyEventListener(Input.KEY_UP, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.UP,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
        this.handler.AddKeyEventListener(Input.KEY_DOWN, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(ProxyScope.ScopeStorage storage) {
                storage.getPlayerHandler().move(PlayerHandler.Direction.DOWN,
                        storage.getMapHandler(), storage.getFrameStorage());
            }
        });
    }
}

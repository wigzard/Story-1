package com.story.game.builders;

import com.story.core.entities.Player;
import com.story.game.action.IKeyAction;
import com.story.game.action.KeyEventHandler;
import com.story.game.mediators.GameplayMediator;
import com.story.game.mediators.IGameplaymediator;
import com.story.game.storages.GlobalStorage;
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
    public void createKeyEvents(IKeyAction handler){
        this.gameplaymediator.setActionHandler(handler);
        initKeysEvent(handler);
    }

    @Override
    public IGameplaymediator getInstance(){
        return this.gameplaymediator;
    }

    private void initKeysEvent(IKeyAction handler){
        handler.addKeyEventListener(Input.KEY_LEFT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(GlobalStorage.ScopeStorage storage) {
                storage.getMapComponent().getPlayerComponent().move(Player.Direction.LEFT,
                        storage.getMapComponent(), storage.getCentralFrameStorage());

                storage.getMapComponent().getNpcList().get(0).move(Player.Direction.LEFT,
                        storage.getMapComponent(), storage.getFrameStorage());
            }
        });
        handler.addKeyEventListener(Input.KEY_RIGHT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(GlobalStorage.ScopeStorage storage) {
                storage.getMapComponent().getPlayerComponent().move(Player.Direction.RIGHT,
                        storage.getMapComponent(), storage.getCentralFrameStorage());

                //storage.getSimpleNPC().get(0).move(Player.Direction.RIGHT,
                 //       storage.getMapHandler(), storage.getFrameStorage());
            }
        });
        handler.addKeyEventListener(Input.KEY_UP, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(GlobalStorage.ScopeStorage storage) {
                storage.getMapComponent().getPlayerComponent().move(Player.Direction.UP,
                        storage.getMapComponent(), storage.getCentralFrameStorage());
            }
        });
        handler.addKeyEventListener(Input.KEY_DOWN, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute(GlobalStorage.ScopeStorage storage) {
                storage.getMapComponent().getPlayerComponent().move(Player.Direction.DOWN,
                        storage.getMapComponent(), storage.getCentralFrameStorage());
            }
        });
    }
}

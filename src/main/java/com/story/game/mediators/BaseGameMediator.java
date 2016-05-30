package com.story.game.mediators;

import com.story.core.IGameMediator;
import com.story.core.entities.Npc;
import com.story.utils.frames.Frame;
import com.story.game.storages.GlobalStorage;
import com.story.modules.global.Converter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public class BaseGameMediator implements IGameMediator {
    private IGameplaymediator gameplayMediator = null;

    public BaseGameMediator(IGameplaymediator gpMediator) throws SlickException {
        this.gameplayMediator = gpMediator;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        this.renderMap();
        this.renderPlayer();
        this.renderSimpleNPC();
    }

    @Override
    public void update(GameContainer gc, int delta) {
        this.gameplayMediator.onUpdate(gc.getInput());

        //If count of storage for central object > 0, then enable animate
        if (GlobalStorage.getInstance().getScope().getCentralFrameStorage().hasNextFrame()) {
            GlobalStorage.getInstance().getScope().getMapComponent()
                    .getPlayerComponent().getMoveAnimation().update(delta);
        }
        else {
            GlobalStorage.getInstance().getScope().getMapComponent()
                    .getPlayerComponent().getMoveAnimation().setCurrentFrame(0);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        GlobalStorage.ScopeStorage storage = GlobalStorage.getInstance().getScope();

        storage.getMapComponent().init();

        Point mapCoordinates = Converter.ObjectPositionToMapPosition(
                storage.getMapComponent().getPlayerComponent().getCurrentPosition(),
                storage.getMapComponent().getTiledMap().getTileWidth(),
                storage.getMapComponent().getTiledMap().getTileHeight(),
                storage.getMapComponent().getMargin());
        storage.getCentralFrameStorage().addFrame(new Frame(mapCoordinates));
    }

    /**
     * Render the map
     */
    private void renderMap(){
        GlobalStorage.ScopeStorage storage = GlobalStorage.getInstance().getScope();

        //Move map to central object
        if (storage.getCentralFrameStorage().hasNextFrame()){
            storage.getMapComponent().setCenterObject(storage.getCentralFrameStorage()
                    .getNextFrame().getPosition());
        }

        storage.getMapComponent().getTiledMap().render(
                storage.getMapComponent().getMapPosition().x,
                storage.getMapComponent().getMapPosition().y);
    }

    /**
     * Render the player
     */
    private void renderPlayer(){
        Point playerCoordinates = GlobalStorage.getInstance().getScope()
                .getMapComponent().getPlayerComponent().calculateCoordinates(
                        GlobalStorage.getInstance().getScope().getMapComponent(),
                        GlobalStorage.getInstance().getScope().getMapComponent().getPlayerComponent().getCurrentPosition());
        GlobalStorage.getInstance().getScope().getMapComponent().getPlayerComponent().getMoveAnimation()
                .draw(playerCoordinates.x, playerCoordinates.y);
    }

    private void renderSimpleNPC(){
        if ((GlobalStorage.getInstance().getScope().getMapComponent().getNpcList() == null) ||
                (GlobalStorage.getInstance().getScope().getMapComponent().getNpcList().size() == 0)){
            return;
        }

        Point npcCoordinates;
        for (Npc simpleNPC: GlobalStorage.getInstance().getScope().getMapComponent().getNpcList()) {
            npcCoordinates = simpleNPC.calculateCoordinates(
                    GlobalStorage.getInstance().getScope().getMapComponent(),
                            simpleNPC.getCurrentPosition());
            simpleNPC.getMoveAnimation().draw(npcCoordinates.x, npcCoordinates.y);
        }
    }
}

package com.story.game.mediators;

import com.story.core.IGameMediator;
import com.story.core.entities.Npc;
import com.story.core.frames.Frame;
import com.story.modules.global.Converter;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.List;

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
        this.gameplayMediator.ExecuteAction(gc.getInput());

        //If count of storage for central object > 0, then enable animate
        if (this.gameplayMediator.getStorageScope().getCentralFrameStorage().hasNextFrame()) {
            this.gameplayMediator.getStorageScope().getMapComponent()
                    .getPlayerComponent().getMoveAnimation().update(delta);
        }
        else {
            this.gameplayMediator.getStorageScope().getMapComponent()
                    .getPlayerComponent().getMoveAnimation().setCurrentFrame(0);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.gameplayMediator.getStorageScope().getMapComponent().init();

        Point mapCoordinates = Converter.ObjectPositionToMapPosition(
                this.gameplayMediator.getStorageScope().getMapComponent().getPlayerComponent().getCurrentPosition(),
                this.gameplayMediator.getStorageScope().getMapComponent().getTiledMap().getTileWidth(),
                this.gameplayMediator.getStorageScope().getMapComponent().getTiledMap().getTileHeight(),
                this.gameplayMediator.getStorageScope().getMapComponent().getMargin());
        this.gameplayMediator.getStorageScope().getCentralFrameStorage().addFrame(
                new Frame(mapCoordinates));
    }

    /**
     * Render the map
     */
    private void renderMap(){
        //Move map to central object
        if (this.gameplayMediator.getStorageScope().getCentralFrameStorage().hasNextFrame()){
            this.gameplayMediator.getStorageScope().getMapComponent().setCenterObject(
                    this.gameplayMediator.getStorageScope().getCentralFrameStorage()
                            .getNextFrame().getPosition());
        }

        this.gameplayMediator.getStorageScope().getMapComponent().getTiledMap().render(
                this.gameplayMediator.getStorageScope().getMapComponent().getMapPosition().x,
                this.gameplayMediator.getStorageScope().getMapComponent().getMapPosition().y);
    }

    /**
     * Render the player
     */
    private void renderPlayer(){
        Point playerCoordinates = this.gameplayMediator.getStorageScope()
                .getMapComponent().getPlayerComponent().calculateCoordinates(
                        this.gameplayMediator.getStorageScope().getMapComponent(),
                        this.gameplayMediator.getStorageScope().getMapComponent().getPlayerComponent().getCurrentPosition());
        this.gameplayMediator.getStorageScope().getMapComponent().getPlayerComponent().getMoveAnimation()
                .draw(playerCoordinates.x, playerCoordinates.y);
    }

    private void renderSimpleNPC(){
        if ((this.gameplayMediator.getStorageScope().getMapComponent().getNpcList() == null) ||
                (this.gameplayMediator.getStorageScope().getMapComponent().getNpcList().size() == 0)){
            return;
        }

        Point npcCoordinates;
        for (Npc simpleNPC: this.gameplayMediator.getStorageScope().getMapComponent().getNpcList()) {
            npcCoordinates = simpleNPC.calculateCoordinates(
                            this.gameplayMediator.getStorageScope().getMapComponent(),
                            simpleNPC.getCurrentPosition());
            simpleNPC.getMoveAnimation().draw(npcCoordinates.x, npcCoordinates.y);
        }
    }
}

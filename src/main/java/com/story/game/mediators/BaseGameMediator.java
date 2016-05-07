package com.story.game.mediators;

import com.story.core.IGameMediator;
import com.story.core.frames.CentralObject;
import com.story.game.storages.ProxyScope;
import com.story.modules.global.Converter;
import com.story.modules.global.GlobalVar;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public class BaseGameMediator implements IGameMediator {
    private IGameplaymediator gameplayMediator = null;
    private ProxyScope.ScopeStorage systemObjectStorage = null;

    public BaseGameMediator(IGameplaymediator gpMediator) throws SlickException {
        this.gameplayMediator = gpMediator;
        this.systemObjectStorage = this.gameplayMediator.getStorageScope();
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        //Move map to central object
        if (this.systemObjectStorage.getFrameStorage().hasNextFrameOfCentralObject()){
            this.systemObjectStorage.getMapHandler().setCenterObject(
                    this.systemObjectStorage.getFrameStorage().getNextFrameOfCentralObject().getPosition());
        }

        this.systemObjectStorage.getMapHandler().getTiledMap().render(
                this.systemObjectStorage.getMapHandler().getMapPosition().x,
                this.systemObjectStorage.getMapHandler().getMapPosition().y);

        Point playerCoordinates = this.getPlayerCoordinates();
        this.systemObjectStorage.getPlayerHandler().getMoveAnimation()
                .draw(playerCoordinates.x, playerCoordinates.y);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        this.gameplayMediator.ExecuteAction(gc.getInput());

        //If count of storage for central object > 0, then enable animate
        if (this.systemObjectStorage.getFrameStorage().hasNextFrameOfCentralObject()) {
            this.systemObjectStorage.getPlayerHandler().getMoveAnimation().update(delta);
        }
        else {
            this.systemObjectStorage.getPlayerHandler().getMoveAnimation().setCurrentFrame(0);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.systemObjectStorage.getMapHandler().init();
        this.systemObjectStorage.getPlayerHandler().init();

        Point mapCoordinates = Converter.ObjectPositionToMapPosition(
                this.systemObjectStorage.getPlayerHandler().getCurrentPosition(),
                this.systemObjectStorage.getMapHandler().getTiledMap().getTileWidth(),
                this.systemObjectStorage.getMapHandler().getTiledMap().getTileHeight(),
                this.systemObjectStorage.getMapHandler().getMargin());
        this.systemObjectStorage.getFrameStorage().addCentralObjectFrame(new CentralObject(mapCoordinates));
    }

    private Point getPlayerCoordinates() {
        int countTileOfWidth = GlobalVar.Width / 2 / this.systemObjectStorage.getMapHandler().getTiledMap()
                .getTileWidth() * this.systemObjectStorage.getMapHandler().getTiledMap().getTileWidth();
        int countTileOfHeight = GlobalVar.Height / 2 / this.systemObjectStorage.getMapHandler().getTiledMap()
                .getTileHeight() * this.systemObjectStorage.getMapHandler().getTiledMap().getTileHeight();

        Point p = this.systemObjectStorage.getPlayerHandler().getCurrentPosition();
        p.x = this.systemObjectStorage.getMapHandler().getTiledMap().getTileWidth() + countTileOfWidth;
        p.y = this.systemObjectStorage.getMapHandler().getTiledMap().getTileHeight() + countTileOfHeight;

        return p;
    }
}

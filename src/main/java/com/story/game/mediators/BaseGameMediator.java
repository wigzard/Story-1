package com.story.game.mediators;

import com.story.core.Converter;
import com.story.core.GlobalVar;
import com.story.core.IGameMediator;
import com.story.core.baseHandlers.*;
import com.story.core.frames.CentralObject;
import com.story.core.frames.IFrameStorage;
import com.story.game.storages.ProxyScope;
import com.story.game.storages.QueueFrameStorage;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public class BaseGameMediator implements IGameMediator {
    private IGameplaymediator gameplayMediator = null;
    private ProxyScope.ScopeStorage systemObjectStorage = null;
    private KeyEventHandler keysHandler = null;

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
        this.keysHandler.run(gc.getInput());

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

        this.keysHandler = new KeyEventHandler();
        initKeysEvent();
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

    private void initKeysEvent(){
        this.keysHandler.addHandler(Input.KEY_LEFT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                Point endPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                endPoint.x--;

                if ((!systemObjectStorage.getMapHandler().isCanMove(endPoint))
                        || (systemObjectStorage.getFrameStorage().hasNextFrameOfCentralObject())){
                    return;
                }

                systemObjectStorage.getPlayerHandler().setCurrentDirection(PlayerHandler.Direction.LEFT);
                systemObjectStorage.getPlayerHandler().move(PlayerHandler.Direction.LEFT);
                systemObjectStorage.getFrameStorage().addCentralObjectFrames(systemObjectStorage.getMapHandler().
                        buildFrames(startPoint, endPoint));
            }
        });

        this.keysHandler.addHandler(Input.KEY_RIGHT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                Point endPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                endPoint.x++;

                if ((!systemObjectStorage.getMapHandler().isCanMove(endPoint))
                        || (systemObjectStorage.getFrameStorage().hasNextFrameOfCentralObject())){
                    return;
                }

                systemObjectStorage.getPlayerHandler().setCurrentDirection(PlayerHandler.Direction.RIGHT);
                systemObjectStorage.getPlayerHandler().move(PlayerHandler.Direction.RIGHT);
                systemObjectStorage.getFrameStorage().addCentralObjectFrames(systemObjectStorage.getMapHandler()
                        .buildFrames(startPoint, endPoint));
            }
        });

        this.keysHandler.addHandler(Input.KEY_UP, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                Point endPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                endPoint.y--;

                if ((!systemObjectStorage.getMapHandler().isCanMove(endPoint))
                        || (systemObjectStorage.getFrameStorage().hasNextFrameOfCentralObject())){
                    return;
                }

                systemObjectStorage.getPlayerHandler().setCurrentDirection(PlayerHandler.Direction.UP);
                systemObjectStorage.getPlayerHandler().move(PlayerHandler.Direction.UP);
                systemObjectStorage.getFrameStorage().addCentralObjectFrames(systemObjectStorage.getMapHandler()
                        .buildFrames(startPoint, endPoint));
            }
        });

        this.keysHandler.addHandler(Input.KEY_DOWN, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                Point endPoint = systemObjectStorage.getPlayerHandler().getCurrentPosition();
                endPoint.y++;

                if ((!systemObjectStorage.getMapHandler().isCanMove(endPoint))
                        || (systemObjectStorage.getFrameStorage().hasNextFrameOfCentralObject())){
                    return;
                }

                systemObjectStorage.getPlayerHandler().setCurrentDirection(PlayerHandler.Direction.DOWN);
                systemObjectStorage.getPlayerHandler().move(PlayerHandler.Direction.DOWN);
                systemObjectStorage.getFrameStorage().addCentralObjectFrames(systemObjectStorage.getMapHandler()
                        .buildFrames(startPoint, endPoint));
            }
        });
    }
}

package com.story.game;

import com.story.core.Converter;
import com.story.core.GlobalVar;
import com.story.core.IGameMediator;
import com.story.core.baseHandlers.*;
import com.story.core.frames.CentralObject;
import com.story.core.frames.IFrameStorage;
import com.story.game.frames.QueueFrameStorage;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public class BaseGameMediator implements IGameMediator {
    private MapHandler mapHandler = null;
    private PlayerHandler playerHandler = null;
    private KeyEventHandler keysHandler = null;
    private IFrameStorage frameStorage = null;

    public BaseGameMediator(MapHandler startMap, PlayerHandler player) throws SlickException {
        this.mapHandler = startMap;
        this.playerHandler = player;
    }

    @Override
    public void render(GameContainer gc, Graphics g) {
        //Move map to central object
        if (this.frameStorage.hasNextFrameOfCentralObject()){
            mapHandler.setCenterObject(this.frameStorage.getNextFrameOfCentralObject().getPosition());
        }

        this.mapHandler.getTiledMap().render(
                this.mapHandler.getMapPosition().x,
                this.mapHandler.getMapPosition().y);

        Point playerCoordinates = this.getPlayerCoordinates();
        this.playerHandler.getMoveAnimation().draw(playerCoordinates.x, playerCoordinates.y);
    }

    @Override
    public void update(GameContainer gc, int delta) {
        this.keysHandler.run(gc.getInput());

        //If count of frames for central object > 0, then enable animate
        if (this.frameStorage.hasNextFrameOfCentralObject()) {
            this.playerHandler.getMoveAnimation().update(delta);
        }
        else {
            this.playerHandler.getMoveAnimation().setCurrentFrame(0);
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        this.mapHandler.init();
        this.playerHandler.init();

        this.frameStorage = new QueueFrameStorage();
        Point mapCoordinates = Converter.ObjectPositionToMapPosition(
                this.playerHandler.getCurrentPosition(),
                this.mapHandler.getTiledMap().getTileWidth(),
                this.mapHandler.getTiledMap().getTileHeight(),
                this.mapHandler.getMargin());
        this.frameStorage.addCentralObjectFrame(new CentralObject(mapCoordinates));

        this.keysHandler = new KeyEventHandler();
        initKeysEvent();
    }

    private Point getPlayerCoordinates() {
        int countTileOfWidth = GlobalVar.Width / 2 / this.mapHandler.getTiledMap().getTileWidth()
                * this.mapHandler.getTiledMap().getTileWidth();
        int countTileOfHeight = GlobalVar.Height / 2 / this.mapHandler.getTiledMap().getTileHeight()
                * this.mapHandler.getTiledMap().getTileHeight();

        Point p = this.playerHandler.getCurrentPosition();
        p.x = this.mapHandler.getTiledMap().getTileWidth() + countTileOfWidth;
        p.y = this.mapHandler.getTiledMap().getTileHeight() + countTileOfHeight;

        return p;
    }

    private void initKeysEvent(){
        this.keysHandler.addHandler(Input.KEY_LEFT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = playerHandler.getCurrentPosition();
                Point endPoint = playerHandler.getCurrentPosition();
                endPoint.x--;

                if ((!mapHandler.isCanMove(endPoint)) || (frameStorage.hasNextFrameOfCentralObject())){
                    return;
                }

                playerHandler.setCurrentDirection(PlayerHandler.Direction.LEFT);
                playerHandler.move(PlayerHandler.Direction.LEFT);
                frameStorage.addCentralObjectFrames(mapHandler.buildFrames(startPoint, endPoint));
            }
        });

        this.keysHandler.addHandler(Input.KEY_RIGHT, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = playerHandler.getCurrentPosition();
                Point endPoint = playerHandler.getCurrentPosition();
                endPoint.x++;

                if ((!mapHandler.isCanMove(endPoint)) || (frameStorage.hasNextFrameOfCentralObject())){
                    return;
                }

                playerHandler.setCurrentDirection(PlayerHandler.Direction.RIGHT);
                playerHandler.move(PlayerHandler.Direction.RIGHT);
                frameStorage.addCentralObjectFrames(mapHandler.buildFrames(startPoint, endPoint));
            }
        });

        this.keysHandler.addHandler(Input.KEY_UP, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = playerHandler.getCurrentPosition();
                Point endPoint = playerHandler.getCurrentPosition();
                endPoint.y--;

                if ((!mapHandler.isCanMove(endPoint)) || (frameStorage.hasNextFrameOfCentralObject())){
                    return;
                }

                playerHandler.setCurrentDirection(PlayerHandler.Direction.UP);
                playerHandler.move(PlayerHandler.Direction.UP);
                frameStorage.addCentralObjectFrames(mapHandler.buildFrames(startPoint, endPoint));
            }
        });

        this.keysHandler.addHandler(Input.KEY_DOWN, new KeyEventHandler.KeyHandler() {
            @Override
            public void execute() {
                Point startPoint = playerHandler.getCurrentPosition();
                Point endPoint = playerHandler.getCurrentPosition();
                endPoint.y++;

                if ((!mapHandler.isCanMove(endPoint)) || (frameStorage.hasNextFrameOfCentralObject())){
                    return;
                }

                playerHandler.setCurrentDirection(PlayerHandler.Direction.DOWN);
                playerHandler.move(PlayerHandler.Direction.DOWN);
                frameStorage.addCentralObjectFrames(mapHandler.buildFrames(startPoint, endPoint));
            }
        });
    }
}

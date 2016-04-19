package com.story.core;

import com.story.core.baseHandlers.*;
import com.story.modules.dbdata.IViewFacade;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 09.04.16.
 */
public class BaseGameMediator implements IGameMediator {
    private MapHandler mapHandler = null;
    private PlayerHandler playerHandler = null;
    private IViewFacade viewsFacade = null;
    private KeyEventHandler keysHandler = null;

    public BaseGameMediator(IViewFacade viewsFacade, int mapId, int playerId) throws SlickException {
        this.viewsFacade = viewsFacade;
        this.mapHandler = new BaseMapHandler(this.viewsFacade.getMap(mapId));
        this.playerHandler = new BasePlayerHandler(this.viewsFacade.getPlayer(playerId));
        initKeysEvent();
    }

    @Override
    public MapHandler getMapHandler() {
        return this.mapHandler;
    }

    @Override
    public Point getPlayerCoordinates() {
        int countTileOfWidth = GlobalVar.Width / 2 / this.mapHandler.getTiledMap().getTileWidth()
                * this.mapHandler.getTiledMap().getTileWidth();
        int countTileOfHeight = GlobalVar.Height / 2 / this.mapHandler.getTiledMap().getTileHeight()
                * this.mapHandler.getTiledMap().getTileHeight();

        Point p = this.playerHandler.getCurrentPosition();
        p.x = this.mapHandler.getTiledMap().getTileWidth() + countTileOfWidth;
        p.y = this.mapHandler.getTiledMap().getTileHeight() + countTileOfHeight;

        return p;
    }

    @Override
    public void executeKeyEvent(Input input) {
        if (input == null){
            return;
        }

        this.keysHandler.run(input);
    }

    @Override
    public void init() throws SlickException {
        this.mapHandler.init();
        this.playerHandler.init();
        this.mapHandler.setCoordinates(this.playerHandler.getCurrentPosition());
    }

    private void initKeysEvent(){
        this.keysHandler = new KeyEventHandler();

        this.keysHandler.registerCallback(Input.KEY_LEFT, new KeyEventHandler.Callback() {
            @Override
            public void execute() {
                Point temp = playerHandler.getCurrentPosition();
                temp.x--;

                if (mapHandler.isCanMove(temp)){
                    playerHandler.move(PlayerHandler.Direction.LEFT);
                    mapHandler.setCoordinates(playerHandler.getCurrentPosition());
                }
            }
        });

        this.keysHandler.registerCallback(Input.KEY_RIGHT, new KeyEventHandler.Callback() {
            @Override
            public void execute() {
                Point temp = playerHandler.getCurrentPosition();
                temp.x++;

                if (mapHandler.isCanMove(temp)){
                    playerHandler.move(PlayerHandler.Direction.RIGHT);
                    mapHandler.setCoordinates(playerHandler.getCurrentPosition());
                }
            }
        });

        this.keysHandler.registerCallback(Input.KEY_UP, new KeyEventHandler.Callback() {
            @Override
            public void execute() {
                Point temp = playerHandler.getCurrentPosition();
                temp.y--;

                if (mapHandler.isCanMove(temp)){
                    playerHandler.move(PlayerHandler.Direction.UP);
                    mapHandler.setCoordinates(playerHandler.getCurrentPosition());
                }
            }
        });

        this.keysHandler.registerCallback(Input.KEY_DOWN, new KeyEventHandler.Callback() {
            @Override
            public void execute() {
                Point temp = playerHandler.getCurrentPosition();
                temp.y++;

                if (mapHandler.isCanMove(temp)){
                    playerHandler.move(PlayerHandler.Direction.DOWN);
                    mapHandler.setCoordinates(playerHandler.getCurrentPosition());
                }
            }
        });
    }
}

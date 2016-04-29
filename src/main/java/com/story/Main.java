package com.story;

import com.story.game.BaseGameMediator;
import com.story.core.Game;
import com.story.core.GlobalVar;
import com.story.core.IGameMediator;
import com.story.game.handlers.BaseMapHandler;
import com.story.game.handlers.BasePlayerHandler;
import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 29.03.16.
 */
public class Main {
    public static void main(String [] args){
        try
        {
            GlobalVar.Width = 800;
            GlobalVar.Height = 600;
            IViewFacade dataFacade = new DbDataView(GlobalVar.dbName);
            IGameMediator mediator = new BaseGameMediator(new BaseMapHandler(dataFacade.getMap(1)),
                    new BasePlayerHandler(dataFacade.getPlayer(1)));

            AppGameContainer appgc;
            appgc = new AppGameContainer(Game.getInstance("Test", mediator));
            appgc.setDisplayMode(GlobalVar.Width, GlobalVar.Height, false);
            appgc.setTargetFrameRate(GlobalVar.maxFPS);
            appgc.start();
        }
        catch (SlickException ex)
        {
            ex.printStackTrace();
        }

        System.out.print("main");
    }
}

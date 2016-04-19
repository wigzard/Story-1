package com.story;

import com.story.core.BaseGameMediator;
import com.story.core.Game;
import com.story.core.GlobalVar;
import com.story.core.IGameMediator;
import com.story.modules.dbdata.DbDataView;
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
            IGameMediator mediator = new BaseGameMediator(new DbDataView("data.sqlite"), 1, 1);

            AppGameContainer appgc;
            //appgc = new AppGameContainer(new GameSlick(800, 600));
            appgc = new AppGameContainer(Game.getInstance("Test", mediator));
            appgc.setDisplayMode(GlobalVar.Width, GlobalVar.Height, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            ex.printStackTrace();
        }

        System.out.print("main");
    }
}

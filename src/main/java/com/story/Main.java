package com.story;

import com.story.core.Game;
import com.story.core.IGameMediator;
import com.story.game.managers.GameplayManager;
import com.story.game.mediators.BaseGameMediator;
import com.story.modules.global.GlobalVar;
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
            GameplayManager manager = new GameplayManager();
            IGameMediator mediator = new BaseGameMediator(manager.getGameplaymediator());

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

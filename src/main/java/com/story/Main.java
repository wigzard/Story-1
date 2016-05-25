package com.story;

import com.story.core.Game;
import com.story.core.IGameMediator;
import com.story.core.customException.LoadSystemObjectException;
import com.story.game.builders.GameplayFactory;
import com.story.game.factories.ActionFactory;
import com.story.game.factories.DescriptorFacadeFactory;
import com.story.game.factories.FrameStorageFactory;
import com.story.game.mediators.BaseGameMediator;
import com.story.game.mediators.IGameplaymediator;
import com.story.game.scenarion.Scenario;
import com.story.modules.asyncTask.AsyncClass;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.global.GlobalVar;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.List;

/**
 * Created by alex on 29.03.16.
 */
public class Main {
    public static void main(String [] args){
        try
        {
            //Hardcode constants
            Scenario scenario = new Scenario();
            scenario.mapId = 1;
            scenario.playerId = 1;
            scenario.simpleNpcIds = new int[] {2, 3};
            //------------------

            GlobalVar.Width = 800;
            GlobalVar.Height = 600;

            IGameplaymediator gameplaymediator = GameplayFactory.createMediator(ActionFactory.create());
            gameplaymediator.init(DescriptorFacadeFactory.create(GlobalVar.dbName),
                    scenario,
                    FrameStorageFactory.create(),
                    FrameStorageFactory.create());
            IGameMediator mediator = new BaseGameMediator(gameplaymediator);

            AppGameContainer appgc;
            appgc = new AppGameContainer(Game.getInstance("Story", mediator));
            appgc.setDisplayMode(GlobalVar.Width, GlobalVar.Height, false);
            appgc.setTargetFrameRate(GlobalVar.maxFPS);
            appgc.start();
        }
        catch (SlickException | LoadSystemObjectException ex)
        {
            ex.printStackTrace();
        }

        System.out.print("main");
    }
}

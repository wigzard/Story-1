package com.story;


import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.map.Map;
import com.story.modules.dbdata.view.player.Person;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 29.03.16.
 */
public class Main {
    public static void main(String [] args){
        /*try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new GameSlick());
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            ex.printStackTrace();
        }*/

        IViewFacade facade = new DbDataView("data.sqlite");
        Person p = facade.getPlayer(1);

        System.out.print("main");
    }
}

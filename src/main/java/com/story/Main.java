package com.story;


import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.player.Player;

/**
 * Created by alex on 29.03.16.
 */
public class Main {
    public static void main(String [] args){
        IViewFacade facade = new DbDataView("data.sqlite");
        Player p = facade.getPlayer(1);

        System.out.print("main");
    }
}

package com.story.modules.dbdata.managers;

import com.story.modules.dbdata.view.Player;

/**
 * Created by alex on 29.03.16.
 */
public class PlayerManager implements IManager {
    @Override
    public Player getData(int id) {
        return new Player();
    }
}

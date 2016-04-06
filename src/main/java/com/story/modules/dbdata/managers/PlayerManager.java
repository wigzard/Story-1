package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.managers.queryProcesses.PlayerTableProcess;
import com.story.modules.dbdata.view.player.Person;
import com.story.modules.pictureWorker.FacePictureSet;
import com.story.modules.pictureWorker.MoveDirectionPictureSet;

/**
 * Created by alex on 29.03.16.
 */
public class PlayerManager implements IManager {
    private IQueryExecutor executor;

    public PlayerManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public Person getData(int id) {
        Person p = this.getPerson(id);
        if (p == null){
            return null;
        }

        return p;
    }

    private Person getPerson(int id){
        String query = "SELECT *  FROM "
                + PlayerTableProcess.TableName + " WHERE " + PlayerTableProcess.IdField
                + "=" + id;

        PlayerTableProcess process = new PlayerTableProcess(query);
        this.executor.selectExecute(process);
        return process.getPlayer();
    }
}

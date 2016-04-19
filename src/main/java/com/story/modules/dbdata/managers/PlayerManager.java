package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.managers.queryProcesses.PlayerTableProcess;
import com.story.modules.dbdata.view.PersonData;

/**
 * Created by alex on 29.03.16.
 */
public class PlayerManager implements IManager {
    private IQueryExecutor executor;

    public PlayerManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public PersonData getData(int id) {
        PersonData p = this.getPerson(id);
        if (p == null){
            return null;
        }

        return p;
    }

    private PersonData getPerson(int id){
        String query = "SELECT *  FROM "
                + PlayerTableProcess.TableName + " WHERE " + PlayerTableProcess.IdField
                + "=" + id;

        PlayerTableProcess process = new PlayerTableProcess(query);
        this.executor.selectExecute(process);
        return process.getPlayer();
    }
}

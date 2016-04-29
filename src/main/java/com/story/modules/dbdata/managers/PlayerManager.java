package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.managers.queryProcesses.PlayerTableProcess;
import com.story.modules.dbdata.view.PersonDescriptor;

/**
 * Created by alex on 29.03.16.
 */
public class PlayerManager implements IManager {
    private IQueryExecutor executor;

    public PlayerManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public PersonDescriptor getData(int id) {
        PersonDescriptor p = this.getPerson(id);
        if (p == null){
            return null;
        }

        return p;
    }

    private PersonDescriptor getPerson(int id){
        String query = "SELECT *  FROM "
                + PlayerTableProcess.TableName + " WHERE " + PlayerTableProcess.IdField
                + "=" + id;

        PlayerTableProcess process = new PlayerTableProcess(query);
        this.executor.selectExecute(process);
        return process.getPlayer();
    }
}

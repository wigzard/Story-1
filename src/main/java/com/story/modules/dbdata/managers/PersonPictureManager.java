package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;
import com.story.modules.dbdata.managers.queryProcesses.PersonPictureTableProcess;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 01.05.16.
 */
class PersonPictureManager implements IManager {
    private IQueryExecutor executor;

    PersonPictureManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public DBTableDescriptor getData(int id) {
        return this.getDescriptor(id);
    }

    @Override
    public List<DBTableDescriptor> getData(int[] ids) {
        return null;
    }

    private PersonPictureDescriptor getDescriptor(int id){
        String query = "SELECT *  FROM '"
                + PersonPictureDescriptor.DBTableName + "' WHERE " + PersonPictureDescriptor.DBFieldId
                + "=" + id;

        PersonPictureTableProcess process = new PersonPictureTableProcess(query);
        this.executor.selectExecute(process);
        return process.getDescriptor();
    }
}

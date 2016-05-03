package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.descriptor.PictureObjectDescriptor;
import com.story.modules.dbdata.managers.queryProcesses.PictureObjectDescriptorTableProcess;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alex on 30.04.16.
 */
public class PictureObjectManager implements IManager {
    private IQueryExecutor executor;

    public PictureObjectManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public PictureObjectDescriptor getData(int id) {
        return null;
    }

    @Override
    public List<DBTableDescriptor> getData(int[] ids) {
        return this.getDescriptors(ids);
    }

    private List<DBTableDescriptor> getDescriptors(int[] ids){
        String args = Arrays.toString(ids);
        args = args.replace("[", "");
        args = args.replace("]", "");

        String query = "SELECT *  FROM '"
                + PictureObjectDescriptor.DBTableName + "' WHERE " + PictureObjectDescriptor.DBFieldId
                + " in(" + args + ")";

        PictureObjectDescriptorTableProcess process = new PictureObjectDescriptorTableProcess(query);
        this.executor.selectExecute(process);
        return process.getDescriptors();
    }
}

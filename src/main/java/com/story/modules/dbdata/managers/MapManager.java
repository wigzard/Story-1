package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.managers.queryProcesses.MapTableProcess;
import com.story.modules.dbdata.descriptor.MapDescriptor;

import java.util.List;

/**
 * Created by alex on 29.03.16.
 */
public class MapManager implements IManager {
    private IQueryExecutor executor;

    public MapManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public DBTableDescriptor getData(int id) {
        return this.getMap(id);
    }

    @Override
    public List<DBTableDescriptor> getData(int[] ids) {
        return null;
    }

    /**
     *
     * @param mapId
     * @return base information about MapDescriptor
     */
    private MapDescriptor getMap(int mapId){
        String query = "SELECT * FROM " +
                MapDescriptor.DBTableName + " WHERE " + MapDescriptor.DBFieldId + "=" + mapId;
        MapTableProcess mapProcess = new MapTableProcess(query);
        this.executor.selectExecute(mapProcess);
        return mapProcess.getData();
    }
}

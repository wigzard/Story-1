package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.managers.queryProcesses.MapTableQueryProcess;
import com.story.modules.dbdata.view.IMapData;
import com.story.modules.dbdata.view.MapData;

/**
 * Created by alex on 29.03.16.
 */
public class MapManager implements IManager {
    private IQueryExecutor executor;

    public MapManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public IMapData getData(int id) {
        MapData resultMapData = this.getMap(id);

        return resultMapData;
    }

    /**
     *
     * @param mapId
     * @return base information about MapData
     */
    private MapData getMap(int mapId){
        String query = "SELECT * FROM " +
                MapTableQueryProcess.TableName + " WHERE " + MapTableQueryProcess.IdField + "=" + mapId;
        MapTableQueryProcess mapProcess = new MapTableQueryProcess(query);
        this.executor.selectExecute(mapProcess);
        return mapProcess.getData();
    }
}

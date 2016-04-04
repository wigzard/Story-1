package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.managers.queryProcesses.MapTableQueryProcess;
import com.story.modules.dbdata.managers.queryProcesses.PositionTableProcess;
import com.story.modules.dbdata.managers.queryProcesses.TilesTableProcess;
import com.story.modules.dbdata.view.IMapData;
import com.story.modules.dbdata.view.map.Map;
import com.story.modules.dbdata.view.map.Tile;
import com.story.modules.dbdata.view.map.TilePosition;

import java.util.ArrayList;

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
        Map resultMap = this.getMap(id);
        if (resultMap == null) {
            return null;
        }

        resultMap.setOtherTilePositions(this.getPositions(resultMap.getId()));
        resultMap.setTiles(this.getTiles(resultMap.getId()));
        return resultMap;
    }

    /**
     *
     * @param mapId
     * @return base information about Map
     */
    private Map getMap(int mapId){
        String query = "SELECT * FROM " +
                MapTableQueryProcess.TableName + " WHERE " + MapTableQueryProcess.IdField + "=" + mapId;
        MapTableQueryProcess mapProcess = new MapTableQueryProcess(query);
        this.executor.selectExecute(mapProcess);
        return mapProcess.getData();
    }

    /**
     *
     * @param mapId
     * @return All tiles positions not default
     */
    private ArrayList<TilePosition> getPositions(int mapId){
        String query = "SELECT t2." + PositionTableProcess.IdField +
                " ,t2." + PositionTableProcess.XField +
                " ,t2." + PositionTableProcess.YField +
                " ,t2." + PositionTableProcess.TileIdField +
                " FROM " + MapTableQueryProcess.TableName +" t1" +
                " JOIN " + PositionTableProcess.TableName + " t2" +
                " ON t1." + PositionTableProcess.IdField + "=t2." + PositionTableProcess.MapIdField +
                " WHERE t1." + MapTableQueryProcess.IdField + "=" + mapId;

        PositionTableProcess posProcess = new PositionTableProcess(query);
        this.executor.selectExecute(posProcess);

        return posProcess.getTilesPosition();
    }

    /**
     *
     * @param mapId
     * @return all unique tiles for current map
     */
    private ArrayList<Tile> getTiles(int mapId){
        String query = "SELECT * FROM " + TilesTableProcess.TableName +
                " WHERE " + TilesTableProcess.IdField + " IN (" +
                "SELECT " + PositionTableProcess.TileIdField +
                " FROM " + PositionTableProcess.TableName +
                " WHERE " + PositionTableProcess.MapIdField + "=" + mapId + ")";

        TilesTableProcess tilesTableProcess = new TilesTableProcess(query);
        this.executor.selectExecute(tilesTableProcess);

        return tilesTableProcess.getTiles();
    }
}

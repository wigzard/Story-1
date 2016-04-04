package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.view.map.TilePosition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by alex on 30.03.16.
 */
public class PositionTableProcess extends DefaultQueryProcess{
    public static final String TableName = "Positions";
    public static final String IdField = "Id";
    public static final String XField = "X";
    public static final String YField = "Y";
    public static final String TileIdField = "TileId";
    public static final String MapIdField = "MapId";

    private ArrayList<TilePosition> positions;

    public PositionTableProcess(String query) {
        super(query);
    }

    public ArrayList<TilePosition> getTilesPosition(){
        return this.positions;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.positions = null;
                return;
            }

            this.positions = new ArrayList<>();
            while (resultSet.next()) {
                TilePosition temp = new TilePosition(resultSet.getInt(IdField));
                temp.setX(resultSet.getInt(XField));
                temp.setY(resultSet.getInt(YField));
                temp.setTileId(resultSet.getInt(TileIdField));

                this.positions.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

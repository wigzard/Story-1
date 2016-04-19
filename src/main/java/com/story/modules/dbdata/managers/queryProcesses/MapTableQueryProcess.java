package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.view.MapData;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 30.03.16.
 */
public class MapTableQueryProcess extends DefaultQueryProcess{
    public static final String TableName = "Maps";
    public static final String IdField = "Id";
    private static final String DescriptionField = "Description";
    private static final String NameField = "Name";
    private static final String PathToFileField = "PathToFile";

    private MapData mapData;

    public MapTableQueryProcess(String query) {
        super(query);
    }

    public MapData getData(){
        return mapData;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                mapData = null;
                return;
            }

            if (resultSet.next()) {
                mapData = new MapData(resultSet.getInt(IdField));
                mapData.setName(resultSet.getString(NameField));
                mapData.setDescription(resultSet.getString(DescriptionField));
                mapData.setPathToTMX(resultSet.getString(PathToFileField));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

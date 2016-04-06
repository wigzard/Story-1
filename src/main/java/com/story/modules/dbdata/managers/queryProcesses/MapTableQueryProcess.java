package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.view.map.Map;

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

    private Map map;

    public MapTableQueryProcess(String query) {
        super(query);
    }

    public Map getData(){
        return map;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                map = null;
                return;
            }

            if (resultSet.next()) {
                map = new Map(resultSet.getInt(IdField));
                map.setName(resultSet.getString(NameField));
                map.setDescription(resultSet.getString(DescriptionField));
                map.setPathToTMX(resultSet.getString(PathToFileField));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.view.MapDescriptor;

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

    private MapDescriptor mapDescriptor;

    public MapTableQueryProcess(String query) {
        super(query);
    }

    public MapDescriptor getData(){
        return mapDescriptor;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                mapDescriptor = null;
                return;
            }

            if (resultSet.next()) {
                mapDescriptor = new MapDescriptor(resultSet.getInt(IdField));
                mapDescriptor.setName(resultSet.getString(NameField));
                mapDescriptor.setDescription(resultSet.getString(DescriptionField));
                mapDescriptor.setPathToTMX(resultSet.getString(PathToFileField));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

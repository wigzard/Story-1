package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.descriptor.MapDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 30.03.16.
 */
public class MapTableProcess extends DefaultQueryProcess{
    private MapDescriptor mapDescriptor;

    public MapTableProcess(String query) {
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
                mapDescriptor = new MapDescriptor(resultSet.getInt(MapDescriptor.DBFieldId));
                mapDescriptor.setName(resultSet.getString(MapDescriptor.DBFieldName));
                mapDescriptor.setDescription(resultSet.getString(MapDescriptor.DBFieldDescription));
                mapDescriptor.setPathToTMX(resultSet.getString(MapDescriptor.DBFieldPathToFile));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

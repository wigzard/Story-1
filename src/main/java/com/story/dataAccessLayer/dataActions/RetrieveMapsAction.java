package com.story.dataAccessLayer.dataActions;

import com.story.application.Constants;
import com.story.dataAccessLayer.dataDescriptors.MapDescriptor;
import com.story.dataAccessLayer.dbLevel.QueryBuilder;
import com.story.dataAccessLayer.dbLevel.QueryDescriptor;
import com.story.dataAccessLayer.dbLevel.tables.MapsTable;
import com.story.utils.log.Trace;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 13.07.16.
 * Class, which is retrieving data from database
 */
public class RetrieveMapsAction extends SingleRecordAction {
    /**
     * Object, that will store data from DB
     */
    private MapDescriptor mapDescriptor;

    /**
     * Create new instance of RetrieveMapsAction
     */
    public RetrieveMapsAction(String path){
        super(path);
        this.mapDescriptor = null;
    }

    /**
     * Create new instance of RetrieveMapsAction with default path to database
     */
    public RetrieveMapsAction(){
        this(Constants.SystemDatabasePath);
    }

    /**
     * Retrieve data by id of record
     * @param id the object Id
     * @return Instance of MapDescriptor
     */
    public MapDescriptor RetrieveObjectById(int id) {
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.selectAll()
                .from(MapsTable.TableName)
                .where()
                .equals(MapsTable.IdFieldName, String.valueOf(id)).toString();

        QueryDescriptor descriptor = new QueryDescriptor(query);

        descriptor.setRespondHandler(resultSet -> {
            parseSingleRecord(resultSet);
            return null;
        });

        this.execute(descriptor);
        return this.mapDescriptor;
    }

    /**
     * Create map descriptor by single record
     * @param resultSet the record
     */
    private void parseSingleRecord(ResultSet resultSet){
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.mapDescriptor = null;
            }

            if (resultSet.next()) {
                this.mapDescriptor = new MapDescriptor(resultSet.getInt(MapsTable.IdFieldName));
                this.mapDescriptor.setName(resultSet.getString(MapsTable.NameFieldName));
                this.mapDescriptor.setDescription(resultSet.getString(MapsTable.DescriptionFieldName));
                this.mapDescriptor.setPathToTMX(resultSet.getString(MapsTable.PathToFileFieldName));
            }

        } catch (SQLException e) {
            Trace.error(e.getMessage(), e);
            this.mapDescriptor = null;
        }
    }

    /**
     * Dispose this object
     */
    @Override
    public void dispose(){
        super.dispose();
        this.mapDescriptor = null;
    }
}

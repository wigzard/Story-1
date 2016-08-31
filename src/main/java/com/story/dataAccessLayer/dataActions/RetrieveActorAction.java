package com.story.dataAccessLayer.dataActions;

import com.story.application.Constants;
import com.story.dataAccessLayer.dataDescriptors.ActorDescriptor;
import com.story.dataAccessLayer.dbLevel.QueryBuilder;
import com.story.dataAccessLayer.dbLevel.QueryDescriptor;
import com.story.dataAccessLayer.dbLevel.tables.ActorTable;
import com.story.utils.log.Trace;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 03.08.16.
 * Retrieves the person data from database
 */
public class RetrieveActorAction extends SingleRecordAction {

    /**
     * The data of person
     */
    private ActorDescriptor personDescriptor;

    /**
     * Initialize new instance of SingleRecordAction and set url
     *
     * @param url path to database
     */
    public RetrieveActorAction(String url) throws Exception {
        super(url);
    }

    /**
     * Initialize new instance of SingleRecordAction
     */
    public RetrieveActorAction() throws Exception {
        super(Constants.SystemDatabasePath);
    }

    /**
     * Retrieve the person descriptor by person id
     * @param id the id of record from database
     * @return the instance of {@link ActorDescriptor}
     */
    public ActorDescriptor retrievePersonById(int id){
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.selectAll()
                .from(ActorTable.TableName)
                .where()
                .equals(ActorTable.IdFieldName, String.valueOf(id)).toString();

        QueryDescriptor descriptor = new QueryDescriptor(query);

        descriptor.setRespondHandler(resultSet -> {
            parseSingleRecord(resultSet);
            return null;
        });

        this.execute(descriptor);
        return this.personDescriptor;
    }

    /**
     * Create person descriptor by single record
     * @param resultSet the record
     */
    private void parseSingleRecord(ResultSet resultSet){
        try{
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.personDescriptor = null;
                return;
            }

            if (resultSet.next()) {
                this.personDescriptor = new ActorDescriptor(resultSet.getInt(ActorTable.IdFieldName));
                this.personDescriptor.setName(resultSet.getString(ActorTable.NameFieldName));
                this.personDescriptor.setSpriteSheetPath(resultSet.getString(ActorTable.SpriteSheetPathFieldName));
            }
        }
        catch (SQLException e){
            Trace.error(e);
            this.personDescriptor = null;
        }
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}

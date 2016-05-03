package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.descriptor.PersonDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 03.04.16.
 */
public class PersonTableProcess extends DefaultQueryProcess {
    private PersonDescriptor player;

    public PersonTableProcess(String query) {
        super(query);
    }

    public PersonDescriptor getPlayer(){
        return this.player;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.player = null;
                return;
            }

            if (resultSet.next()) {
                this.player = new PersonDescriptor(resultSet.getInt(PersonDescriptor.DBFieldId));
                this.player.setName(resultSet.getString(PersonDescriptor.DBFieldName));
                this.player.setPathFacePictureSet(resultSet.getString(PersonDescriptor.DBFieldFacePictureSetField));
                this.player.setPictureDescriptorId(resultSet.getInt(PersonDescriptor.DBFieldPictureDescriptorId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

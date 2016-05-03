package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 01.05.16.
 */
public class PersonPictureTableProcess extends DefaultQueryProcess {
    private PersonPictureDescriptor descriptor;

    public PersonPictureTableProcess(String query) {
        super(query);
    }

    public PersonPictureDescriptor getDescriptor(){
        return this.descriptor;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.descriptor = null;
                return;
            }

            if (resultSet.next()) {
                this.descriptor = new PersonPictureDescriptor(resultSet.getInt(PersonPictureDescriptor.DBFieldId));
                this.descriptor.setBackId(resultSet.getInt(PersonPictureDescriptor.DBFieldBackId));
                this.descriptor.setLeftId(resultSet.getInt(PersonPictureDescriptor.DBFieldLeftId));
                this.descriptor.setRightId(resultSet.getInt(PersonPictureDescriptor.DBFieldRightId));
                this.descriptor.setProfileId(resultSet.getInt(PersonPictureDescriptor.DBFieldProfileId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

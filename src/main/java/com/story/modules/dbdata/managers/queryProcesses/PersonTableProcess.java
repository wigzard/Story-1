package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.descriptor.PersonDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 03.04.16.
 */
public class PersonTableProcess extends DefaultQueryProcess {
    private List<DBTableDescriptor> personDescriptors;

    public PersonTableProcess(String query) {
        super(query);
    }

    public List<DBTableDescriptor> getPersonDescriptors(){
        return this.personDescriptors;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.personDescriptors = null;
                return;
            }

            this.personDescriptors = new ArrayList<>();
            while (resultSet.next()) {
                PersonDescriptor personDescriptor = new PersonDescriptor(resultSet.getInt(PersonDescriptor.DBFieldId));
                personDescriptor.setName(resultSet.getString(PersonDescriptor.DBFieldName));
                personDescriptor.setPathFacePictureSet(resultSet.getString(PersonDescriptor.DBFieldFacePictureSetField));
                personDescriptor.setPictureDescriptorId(resultSet.getInt(PersonDescriptor.DBFieldPictureDescriptorId));

                this.personDescriptors.add(personDescriptor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

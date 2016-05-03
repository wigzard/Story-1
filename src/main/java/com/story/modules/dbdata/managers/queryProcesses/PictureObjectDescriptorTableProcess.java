package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;
import com.story.modules.dbdata.descriptor.PictureObjectDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by alex on 30.04.16.
 */
public class PictureObjectDescriptorTableProcess extends DefaultQueryProcess {
    private ArrayList<DBTableDescriptor> descriptors;

    public PictureObjectDescriptorTableProcess(String query) {
        super(query);
    }

    public ArrayList<DBTableDescriptor> getDescriptors(){
        return this.descriptors;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.descriptors = null;
                return;
            }

            this.descriptors = new ArrayList<>();
            while (resultSet.next()) {
                PictureObjectDescriptor descriptor = new PictureObjectDescriptor(resultSet.getInt(PictureObjectDescriptor.DBFieldId));
                descriptor.setCountOfFrame(resultSet.getInt(PictureObjectDescriptor.DBFieldCountOfFrame));
                descriptor.setImagePath(resultSet.getString(PictureObjectDescriptor.DBFieldImagePath));
                descriptor.setTileHeight(resultSet.getInt(PictureObjectDescriptor.DBFieldTileHeight));
                descriptor.setTileWidth(resultSet.getInt(PictureObjectDescriptor.DBFieldTileWidth));
                descriptor.setDefaultPosition(resultSet.getInt(PictureObjectDescriptor.DBFieldDefaultColumn),
                        resultSet.getInt(PictureObjectDescriptor.DBFieldDefaultRow));
                descriptor.setStartPosition(resultSet.getInt(PictureObjectDescriptor.DBFieldStartRow),
                        resultSet.getInt(PictureObjectDescriptor.DBFieldStartColumn));

                this.descriptors.add(descriptor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

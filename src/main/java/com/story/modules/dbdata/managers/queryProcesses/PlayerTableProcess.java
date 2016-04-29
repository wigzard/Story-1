package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.view.PersonDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 03.04.16.
 */
public class PlayerTableProcess extends DefaultQueryProcess {
    public static final String TableName = "Person";
    public static final String IdField = "Id";
    public static final String NameField = "Name";
    public static final String PathPersonPictureSetField = "PathPersonPictureSet";
    public static final String PathFacePictureSetField = "PathFacePictureSet";

    private PersonDescriptor player;

    public PlayerTableProcess(String query) {
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
                this.player = new PersonDescriptor(resultSet.getInt(IdField));
                this.player.setName(resultSet.getString(NameField));
                this.player.setPathFacePictureSet(resultSet.getString(PathFacePictureSetField));
                this.player.setPathPersonPictureSet(resultSet.getString(PathPersonPictureSetField));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

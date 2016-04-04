package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.view.player.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 03.04.16.
 */
public class PlayerTableProcess extends DefaultQueryProcess {
    public static final String TableName = "PersonaDescription";
    public static final String IdField = "Id";
    public static final String TitleField = "Title";
    public static final String MovePictureSetIdField = "MovePictureSetId";
    public static final String FacePictureSetIdField = "FacePictureSetId";

    private Player player;

    public PlayerTableProcess(String query) {
        super(query);
    }

    public Player getPlayer(){
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
                this.player = new Player(resultSet.getInt(IdField));
                this.player.setTitle(resultSet.getString(TitleField));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

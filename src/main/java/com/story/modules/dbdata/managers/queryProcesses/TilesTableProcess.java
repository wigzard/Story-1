package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.dbdata.view.Tile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by alex on 31.03.16.
 */
public class TilesTableProcess extends DefaultQueryProcess {
    public static final String TableName = "Tiles";
    public static final String IdField = "Id";
    private static final String SrcField = "Src";
    private static final String WidthField = "Width";
    private static final String HeightField = "Height";

    private ArrayList<Tile> tiles;

    public TilesTableProcess(String query) {
        super(query);
    }

    public ArrayList<Tile> getTiles(){
        return this.tiles;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.tiles = null;
                return;
            }

            this.tiles = new ArrayList<>();
            while (resultSet.next()) {
                Tile temp = new Tile(resultSet.getInt(IdField));
                temp.setSrc(resultSet.getString(SrcField));
                temp.setWidth(resultSet.getInt(WidthField));
                temp.setHeight(resultSet.getInt(HeightField));

                this.tiles.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

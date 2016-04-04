package com.story.modules.dbdata.managers;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbdata.managers.queryProcesses.PersonEmotionTableProcess;
import com.story.modules.dbdata.managers.queryProcesses.PersonMoveTableProcess;
import com.story.modules.dbdata.managers.queryProcesses.PlayerTableProcess;
import com.story.modules.dbdata.view.player.Player;
import com.story.modules.pictureWorker.FacePictureSet;
import com.story.modules.pictureWorker.MoveDirectionPictureSet;

/**
 * Created by alex on 29.03.16.
 */
public class PlayerManager implements IManager {
    private IQueryExecutor executor;

    public PlayerManager(IQueryExecutor executor){
        this.executor = executor;
    }

    @Override
    public Player getData(int id) {
        Player p = this.getPlayer(id);
        if (p == null){
            return null;
        }

        p.setFaceSet(this.getFacePictureSet(p.getId()));
        p.setMoveDirection(this.getMovePictureSet(p.getId()));

        return p;
    }

    private Player getPlayer(int id){
        String query = "SELECT " + PlayerTableProcess.IdField + "," + PlayerTableProcess.TitleField
                + " FROM " + PlayerTableProcess.TableName + " WHERE " + PlayerTableProcess.IdField
                + "=" + id;

        PlayerTableProcess process = new PlayerTableProcess(query);
        this.executor.selectExecute(process);
        return process.getPlayer();
    }

    private FacePictureSet getFacePictureSet(int playerId){
        String query = "SELECT t2.* FROM " + PlayerTableProcess.TableName + " t1"
                + " JOIN " + PersonEmotionTableProcess.TableName + " t2"
                + " ON t1." + PlayerTableProcess.FacePictureSetIdField + "=t2." + PersonEmotionTableProcess.IdField
                + " WHERE t1." + PlayerTableProcess.IdField + "=" + playerId;

        PersonEmotionTableProcess process = new PersonEmotionTableProcess(query);
        this.executor.selectExecute(process);
        return process.getFacePictureSet();
    }

    private MoveDirectionPictureSet getMovePictureSet(int playerId){
        String query = "SELECT t2.* FROM " + PlayerTableProcess.TableName + " t1"
                + " JOIN " + PersonMoveTableProcess.TableName + " t2"
                + " ON t1." + PlayerTableProcess.MovePictureSetIdField + "=t2." + PersonMoveTableProcess.IdField
                + " WHERE t1." + PlayerTableProcess.IdField + "=" + playerId;

        PersonMoveTableProcess process = new PersonMoveTableProcess(query);
        this.executor.selectExecute(process);
        return process.getPictureSet();
    }
}

package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.pictureWorker.FacePictureSet;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 03.04.16.
 */
public class PersonEmotionTableProcess extends DefaultQueryProcess {
    public static final String TableName = "PersonEmotionPictureSet";
    public static final String IdField = "Id";
    private static final String UsualField = "Usual";
    private static final String SmirkField = "Smirk";
    private static final String AngerField = "Anger";
    private static final String ShockField = "Shock";
    private static final String SorrowField = "Sorrow";
    private static final String HarmonyField = "Harmony";
    private static final String LaughField = "Laugh";
    private static final String DistressField = "Distressed";

    private FacePictureSet faceSet;

    public PersonEmotionTableProcess(String query) {
        super(query);
    }

    public FacePictureSet getFacePictureSet(){
        return this.faceSet;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.faceSet = null;
                return;
            }

            if (resultSet.next()) {
                this.faceSet = new FacePictureSet(resultSet.getInt(IdField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.USUAL, resultSet.getString(UsualField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.SMIRK, resultSet.getString(SmirkField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.ANGER, resultSet.getString(AngerField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.SHOCK, resultSet.getString(ShockField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.SORROW, resultSet.getString(SorrowField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.HARMONY, resultSet.getString(HarmonyField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.LAUGH, resultSet.getString(LaughField));
                this.faceSet.addDirectionPicture(FacePictureSet.Emotion.DISTRESSED, resultSet.getString(DistressField));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

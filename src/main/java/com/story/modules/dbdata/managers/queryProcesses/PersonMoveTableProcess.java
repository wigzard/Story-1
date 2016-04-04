package com.story.modules.dbdata.managers.queryProcesses;

import com.story.modules.dbWorker.DefaultQueryProcess;
import com.story.modules.pictureWorker.MoveDirectionPictureSet;
import com.story.modules.pictureWorker.PersonAnimatePictureSet;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by alex on 03.04.16.
 */
public class PersonMoveTableProcess extends DefaultQueryProcess {
    private MoveDirectionPictureSet pictureSet;

    public static final String TableName = "PersonMovePictureSet";
    public static final String IdField = "Id";
    private static final String UpStandField = "UpDirection_Stand";
    private static final String UpStep1Field = "UpDirection_Step1";
    private static final String UpStep2Field = "UpDirection_Step2";
    private static final String LeftStandField = "LeftDirection_Stand";
    private static final String LeftStep1Field = "LeftDirection_Step1";
    private static final String LeftStep2Field = "LeftDirection_Step2";
    private static final String DownStandField = "DownDirection_Stand";
    private static final String DownStep1Field = "DownDirection_Step1";
    private static final String DownStep2Field = "DownDirection_Step2";
    private static final String RightStandField = "RightDirection_Stand";
    private static final String RightStep1Field = "RightDirection_Step1";
    private static final String RightStep2Field = "RightDirection_Step2";

    public PersonMoveTableProcess(String query) {
        super(query);
    }

    public MoveDirectionPictureSet getPictureSet(){
        return this.pictureSet;
    }

    @Override
    public void processData(ResultSet resultSet) {
        try {
            if ((resultSet == null) || (!resultSet.isBeforeFirst())){
                this.pictureSet = null;
                return;
            }

            this.pictureSet = new MoveDirectionPictureSet();
            PersonAnimatePictureSet tempSet;
            if (resultSet.next()) {
                tempSet = new PersonAnimatePictureSet();
                tempSet.setStand(resultSet.getString(UpStandField));
                tempSet.setStep1(resultSet.getString(UpStep1Field));
                tempSet.setStep2(resultSet.getString(UpStep2Field));
                this.pictureSet.addDirectionPicture(MoveDirectionPictureSet.Direction.UP, tempSet);

                tempSet = new PersonAnimatePictureSet();
                tempSet.setStand(resultSet.getString(LeftStandField));
                tempSet.setStep1(resultSet.getString(LeftStep1Field));
                tempSet.setStep2(resultSet.getString(LeftStep2Field));
                this.pictureSet.addDirectionPicture(MoveDirectionPictureSet.Direction.LEFT, tempSet);

                tempSet = new PersonAnimatePictureSet();
                tempSet.setStand(resultSet.getString(DownStandField));
                tempSet.setStep1(resultSet.getString(DownStep1Field));
                tempSet.setStep2(resultSet.getString(DownStep2Field));
                this.pictureSet.addDirectionPicture(MoveDirectionPictureSet.Direction.DOWN, tempSet);

                tempSet = new PersonAnimatePictureSet();
                tempSet.setStand(resultSet.getString(RightStandField));
                tempSet.setStep1(resultSet.getString(RightStep1Field));
                tempSet.setStep2(resultSet.getString(RightStep2Field));
                this.pictureSet.addDirectionPicture(MoveDirectionPictureSet.Direction.RIGHT, tempSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

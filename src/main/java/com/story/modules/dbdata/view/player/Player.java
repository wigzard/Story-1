package com.story.modules.dbdata.view.player;

import com.story.modules.dbdata.view.IMapData;
import com.story.modules.pictureWorker.FacePictureSet;
import com.story.modules.pictureWorker.MoveDirectionPictureSet;

/**
 * Created by alex on 29.03.16.
 */
public class Player implements IMapData {
    private int id;
    private String title;
    private MoveDirectionPictureSet moveDirectionSet;
    private FacePictureSet faceSet;

    public Player(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MoveDirectionPictureSet getMoveDirection() {
        return moveDirectionSet;
    }

    public void setMoveDirection(MoveDirectionPictureSet moveDirection) {
        this.moveDirectionSet = moveDirection;
    }

    public FacePictureSet getFaceSet() {
        return faceSet;
    }

    public void setFaceSet(FacePictureSet faceSet) {
        this.faceSet = faceSet;
    }
}

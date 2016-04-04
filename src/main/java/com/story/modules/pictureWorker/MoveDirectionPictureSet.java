package com.story.modules.pictureWorker;

import java.util.HashMap;

/**
 * Created by alex on 03.04.16.
 */
public class MoveDirectionPictureSet {
    public enum Direction{UP, LEFT, RIGHT, DOWN}
    private HashMap<Direction, PersonAnimatePictureSet> moveDirection;

    public void addDirectionPicture(Direction key, PersonAnimatePictureSet pictureSet){
        if (this.moveDirection == null){
            this.moveDirection = new HashMap<>();
        }

        this.moveDirection.put(key, pictureSet);
    }

    public PersonAnimatePictureSet getMoveDirection(Direction d) {
        if ((this.moveDirection == null) || (!this.moveDirection.containsKey(d))){
            return null;
        }

        return this.moveDirection.get(d);
    }
}

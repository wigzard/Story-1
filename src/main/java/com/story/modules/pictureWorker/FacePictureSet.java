package com.story.modules.pictureWorker;

import java.util.HashMap;

/**
 * Created by alex on 03.04.16.
 */
public class FacePictureSet {
    public int getId() {
        return id;
    }

    public enum Emotion{USUAL, SMIRK, ANGER, SHOCK, SORROW, HARMONY, LAUGH, DISTRESSED}
    private HashMap<Emotion, String> faceEmotion;
    private int id;

    public FacePictureSet(int id){
        this.id = id;
    }

    public void addDirectionPicture(Emotion key, String source)
    {
        if (this.faceEmotion == null){
            this.faceEmotion = new HashMap<>();
        }

        this.faceEmotion.put(key, source);
    }

    public String getMoveDirection(Emotion d) {
        if ((this.faceEmotion == null) || (!this.faceEmotion.containsKey(d))){
            return null;
        }

        return this.faceEmotion.get(d);
    }
}

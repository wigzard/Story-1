package com.story.modules.dbdata.descriptor;

/**
 * Created by alex on 01.05.16.
 */
public class PersonPictureDescriptor extends DBTableDescriptor {
    public static final String DBTableName = "Person.PictureDescriptorForSideOfObject";
    public static final String DBFieldId = "Id";
    public static final String DBFieldProfileId = "ProfileId";
    public static final String DBFieldLeftId = "LeftId";
    public static final String DBFieldRightId = "RightId";
    public static final String DBFieldBackId = "BackId";

    public enum ObjectSide {PROFILE, LEFT, RIGHT, BACK}

    private int profileId;
    private int leftId;
    private int rightId;
    private int backId;

    public PersonPictureDescriptor(int id) {
        super(id);
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getLeftId() {
        return leftId;
    }

    public void setLeftId(int leftId) {
        this.leftId = leftId;
    }

    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }

    public int getBackId() {
        return backId;
    }

    public void setBackId(int backId) {
        this.backId = backId;
    }

    public int[] getIdsAsArray(){
        return new int[]{this.backId, this.leftId,
            this.rightId, this.profileId};
    }
}

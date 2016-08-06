package com.story.dataAccessLayer.dataDescriptors;

/**
 * Created by alex on 03.08.16.
 * Represent the person data from database
 */
public class ActorDescriptor extends BaseDescriptor {
    /**
     * The value of name field
     */
    private String name;

    private String spriteSheetPath;

    /**
     * Initialize the instance of {@link ActorDescriptor}
     * @param id the id of person
     */
    public ActorDescriptor(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpriteSheetPath() {
        return spriteSheetPath;
    }

    public void setSpriteSheetPath(String spriteSheetPath) {
        this.spriteSheetPath = spriteSheetPath;
    }

    @Override
    public void dispose() {
        this.name = null;
        this.spriteSheetPath = null;
    }
}

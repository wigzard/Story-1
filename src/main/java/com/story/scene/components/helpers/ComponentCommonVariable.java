package com.story.scene.components.helpers;

import com.story.utils.Size;

/**
 * Created by alex on 21.08.16.
 * Represent common data of components. Uses pattern singleton
 */
public class ComponentCommonVariable {

    /**
     * The instance of {@link ComponentCommonVariable}
     */
    private static ComponentCommonVariable instance;

    /**
     * Stored size of tiles on map. Should be set from MapComponent.
     */
    private Size tileSize;

    /**
     * Initialize new instance of {@link ComponentCommonVariable}
     */
    private ComponentCommonVariable(){}

    /**
     * Get instance of {@link ComponentCommonVariable}
     */
    public static ComponentCommonVariable getInstance(){
        if (instance == null){
            instance = new ComponentCommonVariable();
        }

        return instance;
    }


    public Size getTileSize() {
        return tileSize;
    }

    public void setTileSize(Size tileSize) {
        this.tileSize = tileSize;
    }
}

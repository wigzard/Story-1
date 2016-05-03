package com.story.core;

import com.story.core.baseHandlers.PlayerHandler;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by alex on 29.04.16.
 */
public class Converter {
    public static HashMap <PlayerHandler.Direction, PersonPictureDescriptor.ObjectSide> directionHashMap;

    /**
     *
     * @param object coordinates of object
     * @param tileWidth tile width
     * @param tileHeight tile height
     * @param margin offset from the beginning                           
     * @return map coordinates for object
     */
    public static Point ObjectPositionToMapPosition(Point object, int tileWidth, int tileHeight, int margin){
        object.x += margin;
        object.y += margin;

        Point temp = new Point(((object.x * tileWidth - tileWidth / 2) - GlobalVar.Width / 2) / tileWidth,
                ((object.y * tileHeight - tileHeight / 2) - GlobalVar.Height / 2) / tileHeight);

        temp.x *= tileWidth;
        temp.y *= tileHeight;

        return temp;
    }

    public static PersonPictureDescriptor.ObjectSide ObjectDirectionToObjectSide(PlayerHandler.Direction d){
        if (directionHashMap == null){
            directionHashMap = new HashMap<>();
            directionHashMap.put(PlayerHandler.Direction.UP, PersonPictureDescriptor.ObjectSide.BACK);
            directionHashMap.put(PlayerHandler.Direction.LEFT, PersonPictureDescriptor.ObjectSide.LEFT);
            directionHashMap.put(PlayerHandler.Direction.RIGHT, PersonPictureDescriptor.ObjectSide.RIGHT);
            directionHashMap.put(PlayerHandler.Direction.DOWN, PersonPictureDescriptor.ObjectSide.PROFILE);
        }

        return directionHashMap.get(d);
    }
}

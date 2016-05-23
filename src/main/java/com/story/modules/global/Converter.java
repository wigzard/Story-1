package com.story.modules.global;

import com.story.core.entities.Player;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by alex on 29.04.16.
 */
public class Converter {
    public static HashMap <Player.Direction, PersonPictureDescriptor.ObjectSide> directionHashMap;

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

    public static PersonPictureDescriptor.ObjectSide ObjectDirectionToObjectSide(Player.Direction d){
        if (directionHashMap == null){
            directionHashMap = new HashMap<>();
            directionHashMap.put(Player.Direction.UP, PersonPictureDescriptor.ObjectSide.BACK);
            directionHashMap.put(Player.Direction.LEFT, PersonPictureDescriptor.ObjectSide.LEFT);
            directionHashMap.put(Player.Direction.RIGHT, PersonPictureDescriptor.ObjectSide.RIGHT);
            directionHashMap.put(Player.Direction.DOWN, PersonPictureDescriptor.ObjectSide.PROFILE);
        }

        return directionHashMap.get(d);
    }

    /**
     * Convert array of number to string
     */
    public static String NumbersToString(int[] numbers){
        if ((numbers == null) || (numbers.length == 0)){
            return "";
        }

        String result = "";
        for (int number : numbers) {
            result += number + ",";
        }

        return result.substring(0, result.length() - 1);
    }
}

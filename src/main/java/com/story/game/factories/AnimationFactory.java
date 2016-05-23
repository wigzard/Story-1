package com.story.game.factories;

import com.story.core.entities.PersonEntity;
import com.story.modules.animations.AnimationDescriptor;
import com.story.modules.animations.TemplateFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;
import com.story.modules.dbdata.descriptor.PictureObjectDescriptor;
import com.story.modules.global.Converter;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 30.04.16.
 */
public class AnimationFactory {

    /**
     * Create the animation frame set for player
     * @param d direction of player
     * @param pd player descriptor
     * @return Animation object
     * @throws SlickException
     */
    public static Animation createPlayerAnimation(PersonEntity.Direction d, PersonDescriptor pd){
        try {
            return createPersonAnimation(d, pd, false);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Animation createSimpleNPCAnimation(PersonEntity.Direction d, PersonDescriptor pd){
        try {
            return createPersonAnimation(d, pd, true);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Animation createPersonAnimation(PersonEntity.Direction d, PersonDescriptor pd, boolean isAutoUpdate) throws SlickException {
        PersonPictureDescriptor.ObjectSide side = Converter.ObjectDirectionToObjectSide(d);
        PictureObjectDescriptor personObjectDescriptor = null;
        for (PictureObjectDescriptor ppd: pd.getPictureDescriptors()) {
            if (ppd.getSide() == side){
                personObjectDescriptor = ppd;
                break;
            }
        }

        if (personObjectDescriptor == null){
            return null;
        }

        AnimationDescriptor descriptor = new AnimationDescriptor();
        descriptor.setPath(personObjectDescriptor.getImagePath());
        descriptor.setCountOfFrames(personObjectDescriptor.getCountOfFrame());
        descriptor.setTileSize(personObjectDescriptor.getTileWidth(), personObjectDescriptor.getTileHeight());
        descriptor.setAutoUpdate(isAutoUpdate);

        Point p = new Point(
                personObjectDescriptor.getStartPosition().x * personObjectDescriptor.getTileWidth(),
                personObjectDescriptor.getStartPosition().y * personObjectDescriptor.getTileHeight()
        );

        descriptor.setStartPosition(p);

        return TemplateFactory.getObjectAnimationBuilder().create(descriptor);
    }
}

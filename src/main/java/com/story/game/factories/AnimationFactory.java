package com.story.game.factories;

import com.story.core.Converter;
import com.story.core.baseHandlers.PlayerHandler;
import com.story.modules.animations.AnimationDescriptor;
import com.story.modules.animations.TemplateFactory;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.dbdata.descriptor.PersonPictureDescriptor;
import com.story.modules.dbdata.descriptor.PictureObjectDescriptor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

import java.awt.*;

/**
 * Created by alex on 30.04.16.
 */
public class AnimationFactory {
    public static Animation createPlayerAnimation(PlayerHandler.Direction d, PersonDescriptor pd) throws SlickException {
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

        Point p = new Point(
                personObjectDescriptor.getStartPosition().x * personObjectDescriptor.getTileWidth(),
                personObjectDescriptor.getStartPosition().y * personObjectDescriptor.getTileHeight()
        );

        descriptor.setStartPosition(p);

        return TemplateFactory.getObjectAnimationBuilder().create(descriptor);
    }
}

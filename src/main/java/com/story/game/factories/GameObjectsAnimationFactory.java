package com.story.game.factories;

import com.story.modules.animations.AnimationDescriptor;
import com.story.modules.animations.AnimationFactory;
import com.story.modules.dbdata.view.PersonDescriptor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 30.04.16.
 */
public class GameObjectsAnimationFactory {
    public static Animation createPlayerAnimation(PersonDescriptor pd) throws SlickException {
        AnimationDescriptor descriptor = new AnimationDescriptor();
        descriptor.setPath(pd.getPathPersonPictureSet());
        descriptor.setCountOfFrames(3);
        descriptor.setTileSize(32, 32);

        return AnimationFactory.getObjectAnimationBuilder().create(descriptor);
    }
}

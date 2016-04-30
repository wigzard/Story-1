package com.story.modules.animations;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 29.04.16.
 */
public abstract class AnimationTemplate {
    /**
     * @param descriptor animation descriptor
     * @return animation object by descriptor
     */
    public Animation create(AnimationDescriptor descriptor) throws SlickException {
        return this.createAnimation(descriptor, this.createImage(descriptor.getPath()));
    }

    protected abstract Image createImage(String path) throws SlickException;
    protected abstract Animation createAnimation(AnimationDescriptor descriptor, Image image);
}

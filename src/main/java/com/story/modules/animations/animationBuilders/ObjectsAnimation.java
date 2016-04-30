package com.story.modules.animations.animationBuilders;

import com.story.modules.animations.AnimationTemplate;
import com.story.modules.animations.AnimationDescriptor;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 30.04.16.
 */
public class ObjectsAnimation extends AnimationTemplate {
    protected Image createImage(String pathToFile) throws SlickException {
        if (pathToFile.isEmpty()){
            throw new IllegalArgumentException("Path to file is empty");
        }

        return new Image(pathToFile);
    }

    @Override
    protected Animation createAnimation(AnimationDescriptor descriptor, Image image) {
        Animation animation = new Animation(descriptor.isAutoUpdate());
        int framesCount = descriptor.getCountOfFrames() > 0?
                descriptor.getCountOfFrames() :
                image.getWidth() / descriptor.getTileWidth();

        for (int i = 0; i < framesCount; i++){
            animation.addFrame(image.getSubImage(
                    descriptor.getStartPosition().x + descriptor.getTileWidth() * i,
                    descriptor.getStartPosition().y,
                    descriptor.getTileWidth(),
                    descriptor.getTileHeight()),
                    descriptor.getDuration());
        }
        return animation;
    }
}

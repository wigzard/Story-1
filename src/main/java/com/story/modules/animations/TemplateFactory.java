package com.story.modules.animations;

import com.story.modules.animations.animationBuilders.ObjectsAnimation;

/**
 * Created by alex on 30.04.16.
 */
public class TemplateFactory {

    /**
     * Created instance of the ObjectAnimationBuilder
     * @return new ObjectAnimationBuilder
     */
    public static AnimationTemplate getObjectAnimationBuilder(){
        return new ObjectsAnimation();
    }
}

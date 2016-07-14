package com.story.utils.customException;

/**
 * Created by alex on 13.07.16.
 * Exception, that catch when descriptor is not valid
 */
public class InvalidDescriptor extends Exception {
    public InvalidDescriptor(String message){
        super(message);
    }
}

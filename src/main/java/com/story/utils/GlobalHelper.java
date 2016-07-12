package com.story.utils;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by alex on 12.07.16.
 * Class represent different global function
 */
public class GlobalHelper {

    private GlobalHelper(){}

    /**
     * Check when variable is null or empty
     * @param value check value
     * @return true if value is null or empty
     */
    public static boolean nullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    /**
     * Get URL of resource
     * @param name resource name
     * @return the URL
     */
    public static URL getResource(String name){
        return ClassLoader.getSystemResource(name);
    }
}

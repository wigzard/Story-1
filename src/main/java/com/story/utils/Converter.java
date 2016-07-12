package com.story.utils;

import com.story.utils.log.Trace;

/**
 * Created by alex on 12.07.16.
 * Static class for convert values with different types
 */
public class Converter {

    private Converter(){}

    /**
     * Convert string to integer
     * @param value string, that trying to convert
     * @return integer value
     */
    public static int toInt(String value){
        try {
            return Integer.valueOf(value);
        }
        catch (Exception e){
            Trace.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Convert string to boolean
     * @param value string, that trying to convert
     * @return boolean value
     */
    public static boolean toBoolean(String value){
        try {
            return Boolean.valueOf(value);
        }
        catch (Exception e){
            Trace.error(e.getMessage(), e);
            throw e;
        }
    }

}

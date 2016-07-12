package com.story.utils.log;

import org.apache.log4j.Logger;

/**
 * Created by alex on 12.07.16.
 */
public class Trace {
    private static Logger log = Logger.getLogger(Trace.class);

    public static void info(Object message){
        log.info(message);
    }

    public static void info(Object message, Throwable t){
        log.info(message, t);
    }

    public static void error(Object message){
        log.error(message);
    }

    public static void error(Object message, Throwable t){
        log.error(message, t);
    }
}

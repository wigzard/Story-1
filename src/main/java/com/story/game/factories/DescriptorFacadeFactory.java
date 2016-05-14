package com.story.game.factories;

import com.story.core.descriptor.IDescriptorFacade;
import com.story.modules.dbdata.DBFacade;

/**
 * Created by alex on 14.05.16.
 */
public class DescriptorFacadeFactory {

    /**
     * Create a instance of DBFacade
     * @param dbName the database name
     * @return instance or null
     */
    public static IDescriptorFacade create(String dbName){
        try{
            return new DBFacade(dbName);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

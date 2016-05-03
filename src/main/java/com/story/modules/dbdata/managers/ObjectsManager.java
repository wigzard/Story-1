package com.story.modules.dbdata.managers;

import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.descriptor.ObjectDescriptor;

import java.util.List;

/**
 * Created by alex on 29.03.16.
 */
public class ObjectsManager implements IManager {
    @Override
    public DBTableDescriptor getData(int id) {
        return new ObjectDescriptor(1);
    }

    @Override
    public List<DBTableDescriptor> getData(int[] ids) {
        return null;
    }
}

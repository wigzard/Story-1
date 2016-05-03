package com.story.modules.dbdata.managers;

import com.story.modules.dbdata.descriptor.DBTableDescriptor;

import java.util.List;

/**
 * Created by alex on 29.03.16.
 */
public interface IManager {
    DBTableDescriptor getData(int id);
    List<DBTableDescriptor> getData(int[] ids);
}

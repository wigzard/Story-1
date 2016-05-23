package com.story.modules.dbdata;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbWorker.QueryExecutor;
import com.story.modules.dbdata.descriptor.DBTableDescriptor;
import com.story.modules.dbdata.managers.IManager;
import com.story.modules.dbdata.managers.MapManager;
import com.story.modules.dbdata.managers.PersonManager;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.dbdata.descriptor.PersonDescriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by alex on 29.03.16.
 */
public class DBFacade implements IDescriptorFacade {
    private enum Managers{MAP, PERSON}
    private HashMap<Managers, IManager> managers;
    private IQueryExecutor queryExecutor;

    public DBFacade(String url) {
        managers = new HashMap<>();
        this.queryExecutor = new QueryExecutor(url);
    }

    @Override
    public MapDescriptor getMap(int mapId)
    {
        if (!this.containsElement(Managers.MAP)){
            this.managers.put(Managers.MAP, new MapManager(this.queryExecutor));
        }

        return (MapDescriptor) (this.managers.get(Managers.MAP).getData(mapId));
    }

    @Override
    public PersonDescriptor getPlayer(int id) {
        if (!this.containsElement(Managers.PERSON)){
            this.managers.put(Managers.PERSON, new PersonManager(this.queryExecutor));
        }

        return (PersonDescriptor) (this.managers.get(Managers.PERSON).getData(id));
    }

    /**
     * Create a list of the Npc descriptor
     * @param descriptorIds id of descriptors
     * @return Npc descriptor list
     */
    @Override
    public List<PersonDescriptor> getNPCDescriptor(int[] descriptorIds) {
        if ((descriptorIds == null) || (descriptorIds.length == 0)){
            return null;
        }

        if (!this.containsElement(Managers.PERSON)){
            this.managers.put(Managers.PERSON, new PersonManager(this.queryExecutor));
        }

        ArrayList<PersonDescriptor> descriptors = new ArrayList<>();
        try {
            List<DBTableDescriptor> tabledescriptors = this.managers.get(Managers.PERSON).getData(descriptorIds);
            if ((tabledescriptors == null) || (tabledescriptors.size() == 0)){
                return null;
            }

            for (DBTableDescriptor d: tabledescriptors) {
                descriptors.add((PersonDescriptor)d);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return descriptors;

        //return (PersonDescriptor) (this.components.get(Managers.PERSON).getData(0));
    }

    private boolean containsElement(Managers m){
        return (this.managers.containsKey(m)) && (this.managers.get(m) == null);
    }
}

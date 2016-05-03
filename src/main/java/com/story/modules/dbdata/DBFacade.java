package com.story.modules.dbdata;

import com.story.core.descriptor.IDescriptorFacade;
import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbWorker.QueryExecutor;
import com.story.modules.dbdata.managers.IManager;
import com.story.modules.dbdata.managers.MapManager;
import com.story.modules.dbdata.managers.ObjectsManager;
import com.story.modules.dbdata.managers.PersonManager;
import com.story.modules.dbdata.descriptor.MapDescriptor;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import com.story.modules.dbdata.descriptor.ObjectDescriptor;

import java.util.HashMap;

/**
 * Created by alex on 29.03.16.
 */
public class DBFacade implements IDescriptorFacade {
    private enum Managers{MAP, PLAYER, OTHER_OBJECT}
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
        if (!this.containsElement(Managers.PLAYER)){
            this.managers.put(Managers.PLAYER, new PersonManager(this.queryExecutor));
        }

        return (PersonDescriptor) (this.managers.get(Managers.PLAYER).getData(id));
    }

    @Override
    public ObjectDescriptor getOtherObject() {
        if (!this.containsElement(Managers.OTHER_OBJECT)){
            this.managers.put(Managers.OTHER_OBJECT, new ObjectsManager());
        }

        return (ObjectDescriptor) (this.managers.get(Managers.OTHER_OBJECT).getData(0));
    }

    private boolean containsElement(Managers m){
        return (this.managers.containsKey(m)) && (this.managers.get(m) == null);
    }
}

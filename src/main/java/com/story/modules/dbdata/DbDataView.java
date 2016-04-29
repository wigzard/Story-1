package com.story.modules.dbdata;

import com.story.modules.dbWorker.IQueryExecutor;
import com.story.modules.dbWorker.QueryExecutor;
import com.story.modules.dbdata.managers.IManager;
import com.story.modules.dbdata.managers.MapManager;
import com.story.modules.dbdata.managers.OtherObjectsManager;
import com.story.modules.dbdata.managers.PlayerManager;
import com.story.modules.dbdata.view.MapDescriptor;
import com.story.modules.dbdata.view.PersonDescriptor;
import com.story.modules.dbdata.view.other.OtherObject;

import java.util.HashMap;

/**
 * Created by alex on 29.03.16.
 */
public class DbDataView implements IViewFacade {
    private enum Managers{MAP, PLAYER, OTHER_OBJECT}
    private HashMap<Managers, IManager> managers;
    private IQueryExecutor queryExecutor;

    public DbDataView(String url) {
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
            this.managers.put(Managers.PLAYER, new PlayerManager(this.queryExecutor));
        }

        return (PersonDescriptor) (this.managers.get(Managers.PLAYER).getData(id));
    }

    @Override
    public OtherObject getOtherObject() {
        if (!this.containsElement(Managers.OTHER_OBJECT)){
            this.managers.put(Managers.OTHER_OBJECT, new OtherObjectsManager());
        }

        return (OtherObject) (this.managers.get(Managers.OTHER_OBJECT).getData(0));
    }

    private boolean containsElement(Managers m){
        return (this.managers.containsKey(m)) && (this.managers.get(m) == null);
    }
}

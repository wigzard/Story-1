package dbData;

import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.MapData;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alex on 29.03.16.
 */
public class LoadingMapDataTest {
    private IViewFacade facade;

    public LoadingMapDataTest(){
        this.facade = new DbDataView("testDB.sqlite");
    }

    @Test
    public void createEmptyObject(){
        assertNull(this.facade.getMap(99999));
    }

    /**
     * Check only base information about map
     */
    @Test
    public void checkBaseData(){
        MapData tempMapData = this.facade.getMap(1);

        assertEquals(tempMapData.getId(), 1);
        assertNotNull(tempMapData.getName());
        assertNotNull(tempMapData.getPathToTMX());
    }

    @Test
    public void manyObjects(){
        MapData m1 = this.facade.getMap(1);
        MapData m2 = this.facade.getMap(2);
        MapData m3 = this.facade.getMap(4);
        MapData m4 = this.facade.getMap(7);
        MapData m5 = this.facade.getMap(5);
        MapData m6 = this.facade.getMap(3);

        assertNotNull(m1);
        assertNotNull(m2);
        assertNull(m3);
        assertNull(m4);
        assertNull(m5);
        assertNotNull(m6);
    }
}

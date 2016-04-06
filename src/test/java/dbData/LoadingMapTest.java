package dbData;

import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.map.Map;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alex on 29.03.16.
 */
public class LoadingMapTest {
    private IViewFacade facade;

    public LoadingMapTest(){
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
        Map tempMap = this.facade.getMap(1);

        assertEquals(tempMap.getId(), 1);
        assertNotNull(tempMap.getName());
        assertNotNull(tempMap.getPathToTMX());
    }

    @Test
    public void manyObjects(){
        Map m1 = this.facade.getMap(1);
        Map m2 = this.facade.getMap(2);
        Map m3 = this.facade.getMap(4);
        Map m4 = this.facade.getMap(7);
        Map m5 = this.facade.getMap(5);
        Map m6 = this.facade.getMap(3);

        assertNotNull(m1);
        assertNotNull(m2);
        assertNull(m3);
        assertNull(m4);
        assertNull(m5);
        assertNotNull(m6);
    }
}

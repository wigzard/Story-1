package dbData;

import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.MapDescriptor;
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
        MapDescriptor tempMapDescriptor = this.facade.getMap(1);

        assertEquals(tempMapDescriptor.getId(), 1);
        assertNotNull(tempMapDescriptor.getName());
        assertNotNull(tempMapDescriptor.getPathToTMX());
    }

    @Test
    public void manyObjects(){
        MapDescriptor m1 = this.facade.getMap(1);
        MapDescriptor m2 = this.facade.getMap(2);
        MapDescriptor m3 = this.facade.getMap(4);
        MapDescriptor m4 = this.facade.getMap(7);
        MapDescriptor m5 = this.facade.getMap(5);
        MapDescriptor m6 = this.facade.getMap(3);

        assertNotNull(m1);
        assertNotNull(m2);
        assertNull(m3);
        assertNull(m4);
        assertNull(m5);
        assertNotNull(m6);
    }
}

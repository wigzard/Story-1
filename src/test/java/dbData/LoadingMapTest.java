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
     * Check only base information about map without tiles
     */
    @Test
    public void checkBaseData(){
        Map tempMap = this.facade.getMap(1);

        assertEquals(tempMap.getId(), 1);
        assertEquals(tempMap.getOtherTilePositions().size(), 4);
        assertNotNull(tempMap.getName());
        assertNotEquals(tempMap.getDefaultTileId(), 0);
        assertNotEquals(tempMap.getHeight(), 0);
        assertNotEquals(tempMap.getWidth(), 0);
    }
}

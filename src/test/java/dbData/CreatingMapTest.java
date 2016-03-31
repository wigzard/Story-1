package dbData;

import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.Map;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alex on 29.03.16.
 */
public class CreatingMapTest {
    private IViewFacade facade;
    private static final String MapClassName = "com.story.modules.dbdata.view.Map";
    private static final String PlayerClassName = "com.story.modules.dbdata.view.Player";
    private static final String OtherObjectClassName = "com.story.modules.dbdata.view.OtherObject";

    public CreatingMapTest(){
        this.facade = new DbDataView("data.sqlite");
    }

    @Test
    public void correctCreation(){
        Map tempMap = this.facade.getMap(1);

        assertNotEquals(tempMap, null);
        assertEquals(tempMap.getClass().getName(), MapClassName);
    }

    @Test
    public void createEmptyObject(){
        assertNull(this.facade.getMap(99999));
    }

    @Test
    public void checkData(){
        Map tempMap = this.facade.getMap(1);

        assertEquals(tempMap.getId(), 1);
        assertNotNull(tempMap.getName());
        assertNotEquals(tempMap.getDefaultTileId(), 0);
        assertNotEquals(tempMap.getHeight(), 0);
        assertNotEquals(tempMap.getWidth(), 0);
    }
}

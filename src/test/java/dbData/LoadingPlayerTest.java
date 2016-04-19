package dbData;

import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.PersonData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by alex on 04.04.16.
 */
public class LoadingPlayerTest {
    private IViewFacade facade;

    public LoadingPlayerTest(){
        this.facade = new DbDataView("testDB.sqlite");
    }

    @Test
    public void createEmptyObject(){
        assertNull(this.facade.getPlayer(99999));
    }

    @Test
    public void checkBaseData(){
        PersonData p = this.facade.getPlayer(3);

        assertNotNull(p);
        assertEquals(p.getId(), 3);
        assertEquals(p.getName(), "Normal name");
        assertEquals(p.getPathPersonPictureSet(), "resources/person.tmx");
        assertEquals(p.getPathFacePictureSet(), "resources/faces.tmx");
    }

    @Test
    public void createManyObject(){
        PersonData p1 = this.facade.getPlayer(1);
        PersonData p2 = this.facade.getPlayer(5);
        PersonData p3 = this.facade.getPlayer(3);
        PersonData p4 = this.facade.getPlayer(2);
        PersonData p5 = this.facade.getPlayer(4);

        assertNotNull(p1);
        assertNull(p2);
        assertNotNull(p3);
        assertNotNull(p4);
        assertNull(p5);
    }
}

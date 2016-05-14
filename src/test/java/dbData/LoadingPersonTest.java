package dbData;

import com.story.modules.dbdata.DBFacade;
import com.story.core.descriptor.IDescriptorFacade;
import com.story.modules.dbdata.descriptor.PersonDescriptor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by alex on 04.04.16.
 */
public class LoadingPersonTest {
    private IDescriptorFacade facade;

    public LoadingPersonTest(){
        this.facade = new DBFacade("testDB.sqlite");
    }

    @Test
    public void createEmptyObject(){
        assertNull(this.facade.getPlayer(99999));
    }

    @Test
    public void checkBaseData(){
        PersonDescriptor p = this.facade.getPlayer(1);

        assertNotNull(p);
        assertEquals(p.getId(), 1);
        assertEquals(p.getName(), "Test name");
        assertEquals(p.getPathFacePictureSet(), "resources/faces.tmx");
        assertNotNull(p.getPictureDescriptors());
        assertEquals(p.getPictureDescriptors().size(), 4);
    }

    @Test
    public void createManyObject(){
        PersonDescriptor p1 = this.facade.getPlayer(1);
        PersonDescriptor p2 = this.facade.getPlayer(5);
        PersonDescriptor p3 = this.facade.getPlayer(3);
        PersonDescriptor p4 = this.facade.getPlayer(1);
        PersonDescriptor p5 = this.facade.getPlayer(4);

        assertNotNull(p1);
        assertNull(p2);
        assertNull(p3);
        assertNotNull(p4);
        assertNull(p5);
    }
}
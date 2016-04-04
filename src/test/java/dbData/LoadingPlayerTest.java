package dbData;

import com.story.modules.dbdata.DbDataView;
import com.story.modules.dbdata.IViewFacade;
import com.story.modules.dbdata.view.player.Player;
import com.story.modules.pictureWorker.FacePictureSet;
import com.story.modules.pictureWorker.MoveDirectionPictureSet;
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
        Player p = this.facade.getPlayer(1);

        assertEquals(p.getId(), 1);
        assertEquals(p.getTitle(), "Start player");
    }

    @Test
    public void createManyObject(){
        Player p1 = this.facade.getPlayer(1);
        Player p2 = this.facade.getPlayer(5);
        Player p3 = this.facade.getPlayer(3);
        Player p4 = this.facade.getPlayer(2);
        Player p5 = this.facade.getPlayer(4);

        assertNotNull(p1);
        assertNull(p2);
        assertNull(p3);
        assertNotNull(p4);
        assertNull(p5);
    }

    @Test
    public void checkFacePictureSet(){
        FacePictureSet fs = this.facade.getPlayer(2).getFaceSet();

        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.USUAL));
        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.ANGER));
        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.DISTRESSED));
        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.HARMONY));
        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.LAUGH));
        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.SHOCK));
        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.SMIRK));
        assertNotNull(fs.getMoveDirection(FacePictureSet.Emotion.SORROW));
    }

    @Test
    public void checkMovePictureSet(){
        MoveDirectionPictureSet ps = this.facade.getPlayer(2).getMoveDirection();

        assertNotNull(ps.getMoveDirection(MoveDirectionPictureSet.Direction.UP));
        assertNotNull(ps.getMoveDirection(MoveDirectionPictureSet.Direction.DOWN));
        assertNotNull(ps.getMoveDirection(MoveDirectionPictureSet.Direction.LEFT));
        assertNotNull(ps.getMoveDirection(MoveDirectionPictureSet.Direction.RIGHT));
    }
}

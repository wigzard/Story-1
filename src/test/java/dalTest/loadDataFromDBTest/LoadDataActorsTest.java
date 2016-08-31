package dalTest.loadDataFromDBTest;

import com.story.dataAccessLayer.dataActions.RetrieveActorAction;
import com.story.dataAccessLayer.dataDescriptors.ActorDescriptor;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by Wigzard on 22.08.2016.
 */


/**
 * This TestClass designed to check the correctness of the RetriveActorAction class  with the database
 **/
public class LoadDataActorsTest {

    RetrieveActorAction retrieveActorAction; //This veriable using for conection to database

    /**
    *  This method checks correctness of connection to the database
    **/
    @Test
    public void SuccessConnectTest() throws Exception {
        retrieveActorAction = new RetrieveActorAction("testDB.sqlite");
        assertNotNull(retrieveActorAction);
    }

    /**
     * This method check the behavior of the object when transfer to him incorrect links
     **/
    @Test
    public void UseIncorrectUrl()  {
        try {
            retrieveActorAction = new RetrieveActorAction("test.sqlite");
        } catch (Exception e) {
            retrieveActorAction = null;
        }
        assertNull(retrieveActorAction);
    }

    /**
     * This method checks correctness of the data obtained from the database
     **/
    @Test
    public void DataBaseTest() {
        try {
            retrieveActorAction = new RetrieveActorAction("testDB.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ActorDescriptor actorDescriptor = retrieveActorAction.retrievePersonById(1);
        assertNotNull(actorDescriptor);
        assertEquals("Player", actorDescriptor.getName());
        assertEquals("resources/player_picture_set.png", actorDescriptor.getSpriteSheetPath());
    }

    /**
     * This method check the behavior of the object ActorDescriptor in assigning incorrect data
     **/
    @Test
    public void UseIncorrectId() {
        try {
            retrieveActorAction = new RetrieveActorAction("testDB.sqlite");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ActorDescriptor actorDescriptor = retrieveActorAction.retrievePersonById(4);
        assertNull(actorDescriptor);
    }

}

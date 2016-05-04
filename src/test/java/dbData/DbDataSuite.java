package dbData;

import org.junit.runner.RunWith;

/**
 * Created by alex on 30.03.16.
 */
@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
        LoadingMapDataTest.class,
        LoadingPersonTest.class
})
public class DbDataSuite {
}

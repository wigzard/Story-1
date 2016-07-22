package dalTest.loadSettings;

import com.story.application.Constants;
import com.story.dataAccessLayer.settings.Settings;
import com.story.dataAccessLayer.settings.SettingsLoader;
import com.story.utils.GlobalHelper;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by alex on 12.07.16.
 */
public class SettingsLoaderTest {

    @Test
    public void checkApplicationSetting(){
        Settings setting = SettingsLoader.getSettings(
                GlobalHelper.getResource(Constants.ApplicationSettingsFile).getPath());

        assertNotNull(setting);
        assertEquals(setting.getScreenWidth(), 640);
        assertEquals(setting.getScreenHeight(), 480);
        assertEquals(setting.isFullScreen(), false);
    }
}

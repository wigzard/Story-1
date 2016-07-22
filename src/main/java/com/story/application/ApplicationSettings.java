package com.story.application;

import com.story.dataAccessLayer.settings.Settings;
import com.story.dataAccessLayer.settings.SettingsLoader;
import com.story.utils.GlobalHelper;

/**
 * Created by alex on 12.07.16.
 * Create the default settings of application
 */
public class ApplicationSettings {
    public static final int DefaultScreenWidth = 640;
    public static final int DefaultScreenHeight = 480;
    public static final boolean DefaultIsFullScreen = false;

    private static Settings settingsInstance = null;

    /**
     * Create instance of Settings or return exists
     * @return instance of Settings
     */
    public static Settings getSettings(){
        if (settingsInstance == null){
            settingsInstance = SettingsLoader.getSettings(
                    GlobalHelper.getResource(Constants.ApplicationSettingsFile).getPath());

            if (settingsInstance == null) {
                settingsInstance = createDefaultSetting();
            }
        }

        return settingsInstance;
    }

    /**
     * Create settings object and set default parameters
     * @return instance of Settings
     */
    private static Settings createDefaultSetting(){
        Settings instance = new Settings();
        instance.setFullScreen(DefaultIsFullScreen);
        instance.setScreenHeight(DefaultScreenHeight);
        instance.setScreenWidth(DefaultScreenWidth);

        return settingsInstance;
    }

    private ApplicationSettings(){}
}

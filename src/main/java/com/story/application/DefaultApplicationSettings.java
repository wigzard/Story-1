package com.story.application;

import com.story.dataAccessLayer.applicationSettings.ApplicationSettings;

/**
 * Created by alex on 12.07.16.
 * Create the default settings of application
 */
public class DefaultApplicationSettings {
    private static final int DefaultScreenWidth = 640;
    private static final int DefaultScreenHeight = 480;
    private static final boolean DefaultIsFullScreen = false;

    private static ApplicationSettings settingsInstance = null;

    /**
     * Create instance of ApplicationSettings or return exists
     * @return instance of ApplicationSettings
     */
    public static ApplicationSettings getDefaultSettings(){
        if (settingsInstance == null){
            settingsInstance = createSettingInstance();
        }

        return settingsInstance;
    }

    /**
     * Create settings object and set default parameters
     * @return instance of ApplicationSettings
     */
    private static ApplicationSettings createSettingInstance(){
        ApplicationSettings instance = new ApplicationSettings();
        instance.setFullScreen(DefaultIsFullScreen);
        instance.setScreenHeight(DefaultScreenHeight);
        instance.setScreenWidth(DefaultScreenWidth);

        return settingsInstance;
    }

    private DefaultApplicationSettings(){}
}

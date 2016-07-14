package com.story.dataAccessLayer.applicationSettings;

import com.story.application.DefaultApplicationSettings;
import com.story.utils.Converter;
import com.story.utils.GlobalHelper;
import com.story.utils.log.Trace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by alex on 12.07.16.
 * Class which reading data from settings xml
 */
public class SettingsLoader {
    private static final String ScreenWidthPropertyName = "screen_width";
    private static final String ScreenHeightPropertyName = "screen_height";
    private static final String FullScreePropertyName = "is_full_screen";

    private String pathToFile;

    /**
     * Initialize new instance of SettingsLoader
     * @param settingsFile The path to file
     */
    private SettingsLoader(String settingsFile){
        this.pathToFile = settingsFile;
    }

    /**
     * Initiate loading the settings data
     * @param settingsFilePath the path to file
     * @return ApplicationSettings object or null if was an error
     */
    public static ApplicationSettings getSettings(String settingsFilePath){
        return new SettingsLoader(settingsFilePath).LoadSetting();
    }

    /**
     * Creates settings object
     * @return instance of ApplicationSettings
     */
    private ApplicationSettings LoadSetting(){
        try{
            FileInputStream file = this.loadFile();
            if (file == null){
                return null;
            }
            ApplicationSettings settings = new ApplicationSettings();
            Properties property = new Properties();
            property.load(file);

            this.getPropertiesProcess(property, settings);

            return settings;
        }
        catch (Exception e){
            Trace.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Reading values from xml and set him to object
     * @param property the properties from file
     * @param settings object for which will loading data
     */
    private void getPropertiesProcess(Properties property, ApplicationSettings settings){
        ApplicationSettings defaultSettings = DefaultApplicationSettings.getDefaultSettings();

        String width = property.getProperty(ScreenWidthPropertyName);
        String height = property.getProperty(ScreenHeightPropertyName);
        String isFullScreen = property.getProperty(FullScreePropertyName);

        settings.setScreenWidth(GlobalHelper.isNullOrEmpty(width)? defaultSettings.getScreenWidth():
                Converter.toInt(width));

        settings.setScreenHeight(GlobalHelper.isNullOrEmpty(height)? defaultSettings.getScreenHeight():
                Converter.toInt(height));

        settings.setFullScreen(GlobalHelper.isNullOrEmpty(isFullScreen)? defaultSettings.isFullScreen():
                Converter.toBoolean(isFullScreen));
    }

    /**
     * Load file, and check him
     * @return the FileInputStream object
     */
    private FileInputStream loadFile() throws FileNotFoundException {
        if ((this.pathToFile == null) || (this.pathToFile.isEmpty())){
            Trace.error("Path is empty", new FileNotFoundException("Path is not set"));
            return null;
        }

        File settingFile = new File(this.pathToFile);
        if (!settingFile.exists()){
            Trace.error("Settings file doesn't exists", new FileNotFoundException("File not found"));
            return null;
        }

        return new FileInputStream(this.pathToFile);
    }
}

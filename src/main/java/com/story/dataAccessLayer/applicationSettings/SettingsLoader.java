package com.story.dataAccessLayer.applicationSettings;

import com.story.utils.log.Trace;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by alex on 12.07.16.
 * Class which reading data from settings xml
 */
public class SettingsLoader {
    private String pathToFile;

    private SettingsLoader(String settingsFile){
        this.pathToFile = settingsFile;
    }

    public ApplicationSettings getSettings(String settingsFilePath){

    }

    /**
     * Load file, and check build
     * @return the Document object
     */
    private Document loadFile() throws JDOMException, IOException {
        if ((this.pathToFile == null) || (this.pathToFile.isEmpty())){
            Trace.error("Path is empty", new Throwable("Path is empty2"));
            return null;
        }

        File settingFile = new File(this.pathToFile);
        if (!settingFile.exists()){
            Trace.error("Settings file doesn't exists", new Throwable("File not found"));
            return null;
        }

        SAXBuilder builder = new SAXBuilder();
        return builder.build(settingFile);
    }
}

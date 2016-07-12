package com.story;

import com.story.application.Constants;
import com.story.dataAccessLayer.applicationSettings.ApplicationSettings;
import com.story.dataAccessLayer.applicationSettings.SettingsLoader;
import com.story.utils.GlobalHelper;
import com.story.utils.log.Trace;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created by alex on 29.03.16.
 */
public class Main {
    public static void main(String [] args){

        ApplicationSettings setting = SettingsLoader.getSettings(
                GlobalHelper.getResource(Constants.ApplicationSettingsFile).getPath());

        /*try {
            AppGameContainer container = new AppGameContainer(new Game(), 640, 480, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        System.out.print("main");*/
        Trace.info("Application is finished");
    }
}

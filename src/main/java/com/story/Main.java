package com.story;

import com.story.application.ApplicationSettings;
import com.story.application.Constants;
import com.story.dataAccessLayer.settings.Settings;
import com.story.utils.log.Trace;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;

/**
 * Created by alex on 29.03.16.
 */
public class Main {
    public static void main(String [] args) throws FileNotFoundException {
        Settings setting = ApplicationSettings.getSettings();

        try {
            AppGameContainer container = new AppGameContainer(new Game(),
                    setting.getScreenWidth(),
                    setting.getScreenHeight(),
                    setting.isFullScreen());
            container.setTargetFrameRate(Constants.MaxFPS);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        Trace.info("Application is finished");
    }
}

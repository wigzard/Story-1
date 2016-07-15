package com.story;

import com.story.application.Constants;
import com.story.dataAccessLayer.applicationSettings.ApplicationSettings;
import com.story.dataAccessLayer.applicationSettings.SettingsLoader;
import com.story.dataAccessLayer.dataActions.RetrieveMapsAction;
import com.story.dataAccessLayer.dataDescriptors.MapDescriptor;
import com.story.utils.GlobalHelper;
import com.story.utils.events.EventType;
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

        try {
            AppGameContainer container = new AppGameContainer(new Game(),
                    setting.getScreenWidth(),
                    setting.getScreenHeight(),
                    setting.isFullScreen());
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }

        Trace.info("Application is finished");
    }
}

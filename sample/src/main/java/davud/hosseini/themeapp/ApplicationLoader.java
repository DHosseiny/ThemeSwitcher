package davud.hosseini.themeapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Davud. ThemeApp project.
 */
public class ApplicationLoader extends Application {

    public static Context applicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationContext = this;
    }
}

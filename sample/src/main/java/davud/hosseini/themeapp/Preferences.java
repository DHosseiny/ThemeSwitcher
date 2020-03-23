package davud.hosseini.themeapp;


import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static final String KEY_BACKGROUND = "background";
    public static final String OVERRIDE_BACKGROUND = "override_background";
    public static final String THEME = "theme";
    public static final String IS_ASSET = "isAsset";
    public static final String THEMES = "themes";

    private static SharedPreferences sharedPreferences;

    private Preferences() {
    }

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = getDefaultSharedPreferences();
        }

        return sharedPreferences;
    }

    private static SharedPreferences getDefaultSharedPreferences() {
        return ApplicationLoader.applicationContext.getSharedPreferences(
                ApplicationLoader.applicationContext.getPackageName(),
                Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }
}

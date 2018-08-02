package ir.parsdroid.themeapp;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    public static final String KEY_BACKGROUND = "background";
    public static final String OVERRIDE_BACKGROUND = "override_background";
    public static final String THEME = "theme";
    public static final String IS_ASSET = "isAsset";

    private static SharedPreferences sharedPreferences;

    private Preferences() {
    }

    public static SharedPreferences getSharedPreferences() {
        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationLoader.applicationContext);
        return sharedPreferences;

    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }
}

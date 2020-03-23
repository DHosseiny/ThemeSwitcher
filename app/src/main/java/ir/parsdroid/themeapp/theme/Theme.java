package ir.parsdroid.themeapp.theme;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorInt;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import ir.parsdroid.themeapp.BR;
import java.util.ArrayList;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;

public class Theme extends BaseObservable {

    @NotNull
    public static final String DEFAULT = "default";
    @NotNull
    public static final String DAY = "day";
    @NotNull
    public static final String NIGHT = "night";

    static ThemeInfo currentTheme;

//    public static final String background = "chatBackground";
//
//    private static Drawable wallpaper = null;

    public static ArrayList<ThemeInfo> themes;
    private static HashMap<String, ThemeInfo> themesDict;

    private static ThemeInfo defaultTheme;

    private static HashMap<String, Integer> currentThemeValues = new HashMap<>();
    private static HashMap<String, Integer> defaultThemeValues = new HashMap<>();

    private static Theme instance;


    @Bindable
    public final String colorPrimary = "colorPrimary";
    @Bindable
    public final String colorPrimaryDark = "colorPrimaryDark";
    @Bindable
    public final String colorPrimaryLight = "colorPrimaryLight";
    @Bindable
    public final String colorAccent = "colorAccent";
    @Bindable
    public final String textColorPrimary = "textColorPrimary";
    @Bindable
    public final String textColorSecondary = "textColorSecondary";
    @Bindable
    public final String textColorTertiary = "textColorTertiary";
    @Bindable
    public final String textColorInverse = "textColorInverse";
    @Bindable
    public final String iconTint = "iconTint";
    @Bindable
    public final String iconInverseTint = "iconInverseTint";
    @Bindable
    public final String divider = "divider";
    @Bindable
    public final String hintText = "hintText";
    @Bindable
    public final String fabIconColor = "fabIconColor";
    @Bindable
    public final String fabBackground = "fabBackground";
    @Bindable
    public final String hyperText = "hyperText";

    //colors that don't need to observe to them
    public static final String windowBackground = "windowBackground";

    static {
        defaultThemeValues.put(getInstance().colorPrimary, 0xFF3f51b5);

        defaultThemeValues.put(getInstance().colorPrimaryLight, 0xFFC5CAE9);

        defaultThemeValues.put(getInstance().colorPrimaryDark, 0xFF303F9F);

        defaultThemeValues.put(getInstance().colorAccent, 0xFF009688);

        defaultThemeValues.put(getInstance().textColorInverse, Color.WHITE);

        defaultThemeValues.put(getInstance().textColorPrimary, Color.BLACK);

        defaultThemeValues.put(getInstance().textColorSecondary, Color.BLACK);

        defaultThemeValues.put(getInstance().textColorTertiary, Color.DKGRAY);

        defaultThemeValues.put(windowBackground, Color.WHITE);

        defaultThemeValues.put(getInstance().iconTint, Color.WHITE);

        defaultThemeValues.put(getInstance().iconInverseTint, Color.BLACK);

        defaultThemeValues.put(getInstance().fabIconColor, Color.WHITE);

        defaultThemeValues.put(getInstance().fabBackground, 0xFF009688);

        defaultThemeValues.put(getInstance().divider, Color.LTGRAY);

        defaultThemeValues.put(getInstance().hintText, Color.LTGRAY);

        defaultThemeValues.put(getInstance().hyperText, Color.BLUE);


        themes = new ArrayList<>();
        themesDict = new HashMap<>();

        ThemeInfo themeInfo = new ThemeInfo(DEFAULT, true);

        themes.add(currentTheme = defaultTheme = themeInfo);
        themesDict.put(DEFAULT, defaultTheme);

        themeInfo = new ThemeInfo(NIGHT, true);
        themes.add(themeInfo);
        themesDict.put(NIGHT, themeInfo);

        themeInfo = new ThemeInfo(DAY, true);
        themes.add(themeInfo);
        themesDict.put(DAY, themeInfo);
    }

    public static Theme getInstance() {
        Theme localInstance = instance;
        if (localInstance == null) {
            synchronized (Theme.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Theme();
                }
            }
        }
        return localInstance;
    }


//    @Bindable
//    public Drawable getWallpaper() {
//        return wallpaper;
//    }

//    public void setWallpaper(Drawable wallpaper) {
//        Theme.wallpaper = wallpaper;
//        notifyPropertyChanged(BR.wallpaper);
//    }
//
//    public static void loadWallpaper(Context context) {
//        int wallpaperId = Preferences.getSharedPreferences().getInt(Preferences.KEY_BACKGROUND, 0);
//        if (wallpaperId == 0) {
//            boolean overrideBackground = Preferences.getSharedPreferences().getBoolean(Preferences.OVERRIDE_BACKGROUND, false);
//
//            if (overrideBackground) {
//                Theme.getInstance().setWallpaper(new ColorDrawable(Theme.getColor(Theme.chatBackground)));// TODO: 6/20/2018 load file from themeInfo.assetFile or themeInfo.filePath
//            } else {
//                Theme.getInstance().setWallpaper(ContextCompat.getDrawable(context, R.drawable.background));
//            }
//        } else {
//            File file = new File(Utilities.getAssetsDir("wallpaper" + wallpaperId));
//            if (file.exists()) {
//                Theme.getInstance().setWallpaper(Drawable.createFromPath(file.getAbsolutePath()));
//            } else {
//                Theme.getInstance().setWallpaper(ContextCompat.getDrawable(context, R.drawable.background));
//            }
//        }
//    }

    public static int getColor(String key) {
        Integer color = currentThemeValues.get(key);
        if (color == null) {
            color = getDefaultColor(key);
        }
        return color;
    }

    private static int getDefaultColor(String key) {
        if (key == null) return 0;
        return defaultThemeValues.get(key);
    }

    public void setColor(String key, int value) {
        currentThemeValues.put(key, value);
    }

    public static boolean isDefaultTheme() {
        return defaultTheme.equals(currentTheme);
    }

    public static boolean isCurrentTheme(ThemeInfo theme) {
        return currentTheme.equals(theme);
    }

    public static void setDrawableColorByKey(Drawable drawable, String key) {
        drawable.setColorFilter(new PorterDuffColorFilter(getColor(key), PorterDuff.Mode.MULTIPLY));
    }

    public void setTheme(@NotNull ThemeInfo theme, HashMap<String, Integer> themeValues, boolean notify) {
        currentTheme = theme;
        currentThemeValues = themeValues;
//        loadWallpaper(ApplicationLoader.applicationContext);
        if (notify)
            notifyPropertyChanged(BR._all);// TODO: 12/18/2018 notify any of colors instead of all.
    }


    public void resetTheme() {
        currentTheme = defaultTheme;
        currentThemeValues = new HashMap<>();
//        loadWallpaper(ApplicationLoader.applicationContext);
        notifyPropertyChanged(BR._all);// TODO: 12/18/2018 notify any of colors instead of all.
    }
}

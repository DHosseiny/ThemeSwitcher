package ir.parsdroid.themeapp;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class Theme extends BaseObservable {

    private static final int THEME_CHANGED = 100;

    @NotNull
    public static final String DEFAULT = "default";
    @NotNull
    public static final String DAY = "day";
    @NotNull
    public static final String NIGHT = "night";

    private static ThemeInfo currentTheme;

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
    public final String windowBackground = "windowBackground";
    @Bindable
    public final String hyperTextOut = "hyperTextOut";
    @Bindable
    public final String background_grey = "background_grey";
    @Bindable
    public final String grey_highlight_text = "grey_highlight_text";
    @Bindable
    public final String blue_replier_name = "blue_replier_name";
    @Bindable
    public final String accentBlue = "accentBlue";
    @Bindable
    public final String pinnedConversation = "pinnedConversation";
    @Bindable
    public final String selected_red = "selected_red";
    @Bindable
    public final String textColorPrimary = "primaryTextColor";
    @Bindable
    public final String textColorSecondary = "textColorSecondary";
    @Bindable
    public final String iconInverseTint = "iconInverseTint";
    @Bindable
    public final String messageBackgroundTintIn = "messageBackgroundTintIn";
    @Bindable
    public final String messageBackgroundTintOut = "messageBackgroundTintOut";
    @Bindable
    public final String messagePropertiesOut = "messagePropertiesOut";
    @Bindable
    public final String dividerColor = "dividerColor";
    @Bindable
    public final String conversationSearchSeparator = "conversationSearchSeparator";
    @Bindable
    public final String subtitleInverseColor = "subtitleInverseColor";
    @Bindable
    public final String conversationIconTint = "conversationIconTint";
    @Bindable
    public final String iconTint = "iconTint";
    @Bindable
    public final String chatTextColor = "chatTextColor";
    @Bindable
    public final String messagePropertiesIn = "messagePropertiesIn";
    @Bindable
    public final String settingsItemBackgroundColor = "settingsItemBackgroundColor";
    @Bindable
    public final String hintTextColor = "hintTextColor";
    @Bindable
    public final String fileNameTextColor = "fileNameTextColor";
    @Bindable
    public final String messageStatusTint = "messageStatusTint";
    @Bindable
    public final String enterViewBg = "enterViewBg";
    @Bindable
    public final String fileSizeTextColorIn = "fileSizeTextColorIn";
    @Bindable
    public final String fileSizeTextColorOut = "fileSizeTextColorOut";
    @Bindable
    public final String readLineBg = "readLineBg";
    @Bindable
    public final String dialogButtonColor = "dialogButtonColor";
    @Bindable
    public final String conversationFabBG = "conversationFabBG";
    @Bindable
    public final String fabIconColor = "conversationFabColor";
    @Bindable
    public final String fabBackground = "fabBackground";
    @Bindable
    public final String fabItemColors = "fabItemColors";

    @Bindable
    public final String chatInputIconTint = "chatInputIconTint";

    static {
        defaultThemeValues.put(getInstance().colorPrimary, 0xFF3f51b5);

        defaultThemeValues.put(getInstance().colorPrimaryLight, 0xFF757de8);

        defaultThemeValues.put(getInstance().colorPrimaryDark, 0xFF002984);

        defaultThemeValues.put(getInstance().colorAccent, 0xFFb2ff59);

        defaultThemeValues.put(getInstance().textColorPrimary, Color.WHITE);

        defaultThemeValues.put(getInstance().textColorSecondary, Color.BLACK);

        defaultThemeValues.put(getInstance().windowBackground, Color.WHITE);

        defaultThemeValues.put(getInstance().iconTint, Color.WHITE);

        defaultThemeValues.put(getInstance().iconInverseTint, Color.BLACK);

        defaultThemeValues.put(getInstance().fabIconColor, 0xFFFFFFFF);

        defaultThemeValues.put(getInstance().fabBackground, 0xFFFFFFFF);

        defaultThemeValues.put(getInstance().fabItemColors, 0xFF737373);

        defaultThemeValues.put(getInstance().settingsItemBackgroundColor, Color.WHITE);

        defaultThemeValues.put(getInstance().pinnedConversation, 0xFFF5F5F5);

        defaultThemeValues.put(getInstance().chatTextColor, 0xdd000000);

        defaultThemeValues.put(getInstance().fileNameTextColor, 0xdd000000);

        defaultThemeValues.put(getInstance().subtitleInverseColor, 0xd9ffffff);

        defaultThemeValues.put(getInstance().messageBackgroundTintIn, 0xffffffff);

        defaultThemeValues.put(getInstance().readLineBg, Color.WHITE);

        defaultThemeValues.put(getInstance().messageBackgroundTintOut, 0xFFEFFFDE);

        defaultThemeValues.put(getInstance().messagePropertiesOut, 0xFF5DC452);

        defaultThemeValues.put(getInstance().messagePropertiesIn, 0xFF999999);

        defaultThemeValues.put(getInstance().hyperTextOut, 0xFF2678B6);

        defaultThemeValues.put(getInstance().hintTextColor, Color.GRAY);

        defaultThemeValues.put(getInstance().conversationIconTint, Color.TRANSPARENT);

        defaultThemeValues.put(getInstance().messageStatusTint, 0xFF46aa36);

        defaultThemeValues.put(getInstance().enterViewBg, Color.WHITE);

        defaultThemeValues.put(getInstance().fileSizeTextColorIn, 0xFFA1AAB3);

        defaultThemeValues.put(getInstance().fileSizeTextColorOut, 0xFF65B05B);

        defaultThemeValues.put(getInstance().dialogButtonColor, 0xFFE53935);//accent color

        defaultThemeValues.put(getInstance().dividerColor, 0xFFE9E9E9);

        defaultThemeValues.put(getInstance().conversationSearchSeparator, 0xFFF0F0F0);

        defaultThemeValues.put(getInstance().conversationFabBG, 0xFFE53935);

        defaultThemeValues.put(getInstance().chatInputIconTint, Color.TRANSPARENT);

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

    public static void setDrawableColorByKey(Drawable drawable, String key) {
        drawable.setColorFilter(new PorterDuffColorFilter(getColor(key), PorterDuff.Mode.MULTIPLY));
    }

    public void setTheme(@NotNull ThemeInfo theme, HashMap<String, Integer> themeFileValues) {
        currentTheme = theme;
        currentThemeValues = themeFileValues;

//        loadWallpaper(ApplicationLoader.applicationContext);
        notifyChange();
        EventBus.getDefault().post(THEME_CHANGED);
    }

    public static void loadTheme(@NotNull ThemeInfo theme, HashMap<String, Integer> themeFileValues) {
        currentTheme = theme;
        currentThemeValues = themeFileValues;
//        loadWallpaper(ApplicationLoader.applicationContext);
    }

    public void resetTheme() {
        currentTheme = defaultTheme;
        currentThemeValues = new HashMap<>();
//        loadWallpaper(ApplicationLoader.applicationContext);
        notifyChange();
        EventBus.getDefault().post(THEME_CHANGED);
    }
}

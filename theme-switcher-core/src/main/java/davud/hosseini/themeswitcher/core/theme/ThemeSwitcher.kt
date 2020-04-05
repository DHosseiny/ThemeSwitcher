/*
 * Created by Davud. ThemeApp project.
 */
package davud.hosseini.themeswitcher.core.theme

import android.app.Activity
import android.content.Context
import davud.hosseini.themeswitcher.core.parser.ParserUtils
import davud.hosseini.themeswitcher.core.preferences.Preferences
import davud.hosseini.themeswitcher.core.preferences.PreferencesImpl

object ThemeSwitcher {

    private lateinit var instance: Theme<*>

    private val parserUtils: ParserUtils by lazy { ParserUtils() }

    fun instantiate(activity: Activity, theme: Theme<*>) {
        if (::instance.isInitialized) return
        instance = theme

        val preferences: Preferences = PreferencesImpl(context = activity)
        val themeName: String = preferences.themeName.ifEmpty { DEFAULT }

        if (themeName != DEFAULT) {

            val isAsset: Boolean = preferences.isAsset
            val colors: Map<String, Int> = parserUtils.readThemeFileValues(activity, themeName, isAsset)
            val themeInfo = ThemeInfo(themeName, isAsset)

            theme.themeInfo = themeInfo
            theme.setColorValues(colors)

            //val overrideChatBackground = preferences.overrideBackground
            //loadWallpaper(activity)
        } //don't need to notify on start

//    must be after setTheme() to load proper colors
//            changeStatusBarColor(activity)
//            setTaskDescription(activity)

    }

    fun changeTheme(themeInfo: ThemeInfo, activity: Activity) {
        val preferences: Preferences = PreferencesImpl(context = activity)

        preferences.themeName = themeInfo.name
        preferences.isAsset = themeInfo.isAsset

        if (themeInfo.name == DEFAULT) {
            resetTheme()
        } else setTheme(themeInfo, parserUtils.readThemeFileValues(activity, themeInfo.name, themeInfo.isAsset))

//            changeStatusBarColor(activity)
//            setTaskDescription(activity)
    }

    fun getThemes(context: Context): Pair<List<ThemeInfo>, Int> {

        val userDefinedThemesInfo = getUserDefinedThemesInfo(context)

        val themeInfoList = mutableListOf<ThemeInfo>()

        themeInfoList.addAll(predefinedThemesInfo)
        themeInfoList.addAll(userDefinedThemesInfo)

        val selectedThemeIndex = themeInfoList.indexOfFirst { it == instance.themeInfo }

        return themeInfoList to selectedThemeIndex
    }

    private fun getUserDefinedThemesInfo(context: Context): List<ThemeInfo> {
//            Preferences.getSharedPreferences(context).getString(Preferences.THEMES, "[]")!!
        return listOf(ThemeInfo("testUserTheme", false))
    }

//        private fun setTaskDescription(activity: Activity) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                try {
//                    activity.setTaskDescription(ActivityManager.TaskDescription(null, null, Theme. (Theme.instance.colorPrimary) or -0x1000000))
//                } catch (ignored: Exception) {
//
//                }
//
//            }
//        }
//
//        private fun changeStatusBarColor(activity: Activity) {
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                val window = activity.window
//                window.statusBarColor = Theme.getColor(Theme.colorPrimaryDark)
//            }
//        }

    private fun setTheme(themeInfo: ThemeInfo, colorValues: Map<String, Int>) {
        instance.themeInfo = themeInfo
        instance.setColorValues(colorValues)
        //        loadWallpaper(ApplicationLoader.applicationContext);
    }

    private fun resetTheme() {
        instance.resetToDefaults()
        //        loadWallpaper(ApplicationLoader.applicationContext);
    }
//private fun notifyPropertyChanged() {
//    instance.colorPrimary.notifyObservers()
//}
}
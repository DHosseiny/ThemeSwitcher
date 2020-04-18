/*
 * Created by Davud. ThemeApp project.
 */
package davud.hosseini.themeswitcher.core.theme

import android.app.Activity
import android.content.Context
import android.os.Looper
import davud.hosseini.themeswitcher.core.parser.ParserUtils
import davud.hosseini.themeswitcher.core.preferences.Preferences
import davud.hosseini.themeswitcher.core.preferences.PreferencesImpl

object ThemeSwitcher {

    lateinit var theme: Theme
        private set

    private val parserUtils: ParserUtils by lazy { ParserUtils() }

    fun setup(activity: Activity) {

        val preferences: Preferences = PreferencesImpl(context = activity)
        val themeName: String = preferences.themeName.ifEmpty { DEFAULT }
        val isAsset: Boolean = preferences.isAsset
        val themeInfo = ThemeInfo(themeName, isAsset)

        setTheme(activity, themeInfo, notifyObservers = false)
    }

    fun changeTheme(themeInfo: ThemeInfo, activity: Activity) {
        checkMainThread()
        val preferences: Preferences = PreferencesImpl(context = activity)

        preferences.themeName = themeInfo.name
        preferences.isAsset = themeInfo.isAsset

        setTheme(activity, themeInfo, notifyObservers = true)
    }

    fun getThemes(context: Context): Pair<List<ThemeInfo>, Int> {

        val userDefinedThemesInfo = getUserDefinedThemesInfo(context)

        val themeInfoList = mutableListOf<ThemeInfo>()

        themeInfoList.addAll(predefinedThemesInfo)
        themeInfoList.addAll(userDefinedThemesInfo)

        val selectedThemeIndex = themeInfoList.indexOfFirst { it == theme.themeInfo }

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

    private fun setTheme(activity: Activity, themeInfo: ThemeInfo, notifyObservers: Boolean) {

        fun onThemeChanged(activity: Activity, notifyObservers: Boolean) {
//            changeStatusBarColor(activity)
//            setTaskDescription(activity)
//            val overrideChatBackground = preferences.overrideBackground
//            loadWallpaper(activity)
            if (notifyObservers) notifyObservers()
        }

        val palette = if (themeInfo.isDefault) {
            defaultPalette
        } else {
            parserUtils.readThemeFileValues(activity, themeInfo)
        }

        theme = Theme(themeInfo, palette)
        onThemeChanged(activity, notifyObservers)
    }

    private val themeChangeListeners: MutableList<(Theme) -> Unit> by lazy(::mutableListOf)

    fun addThemeChangeListener(themeObserver: (Theme) -> Unit) {
        themeChangeListeners.add(themeObserver)
    }

    fun removeThemeChangeListener(themeObserver: (Theme) -> Unit) {
        themeChangeListeners.remove(themeObserver)
    }

    private fun notifyObservers() {
        themeChangeListeners.forEach { it(theme) }
    }

    private fun checkMainThread() {
        check(Thread.currentThread() == Looper.getMainLooper().thread) {
            "Method cannot be called off the main application thread (on: " +
                    "${Thread.currentThread().name})"
        }
    }

}
/*
 * Created by Davud. ThemeApp project.
 */

/*
 * Created by Davud. ThemeApp project.
 */
package davud.hosseini.themeswitcher.core.theme

import android.app.Activity
import android.content.Context
import android.graphics.Color
import davud.hosseini.themeswitcher.core.copyFile
import davud.hosseini.themeswitcher.core.parseInt
import davud.hosseini.themeswitcher.core.preferences.Preferences
import davud.hosseini.themeswitcher.core.preferences.PreferencesImpl
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

private lateinit var instance: Theme<*>

object ThemeSwitcher {
    fun instantiate(activity: Activity, theme: Theme<*>) {
        if (::instance.isInitialized) return
        instance = theme

        val preferences: Preferences = PreferencesImpl(context = activity)
        val themeName: String = preferences.themeName.ifEmpty { DEFAULT }

        if (themeName != DEFAULT) {

            val isAsset: Boolean = preferences.isAsset
            val colors: Map<String, Int> = readThemeFileValues(activity, themeName, isAsset)
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

    fun applyTheme(themeInfo: ThemeInfo, activity: Activity) {
        val preferences: Preferences = PreferencesImpl(context = activity)

        preferences.themeName = themeInfo.name
        preferences.isAsset = themeInfo.isAsset

        if (themeInfo.name == DEFAULT) {
            resetTheme()
        } else setTheme(themeInfo, readThemeFileValues(activity, themeInfo.name, themeInfo.isAsset))

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

    private fun readThemeFileValues(context: Activity, name: String, isAsset: Boolean): Map<String, Int> {

        val mFile = if (isAsset) {
            getAssetFile("$name.attheme", context)
        } else {
            File(context.filesDir, "$name.attheme")
        }


        var stream: FileInputStream? = null
        val colors = mutableMapOf<String, Int>()
        try {
            val bytes = ByteArray(1024)
            var currentPosition = 0

            stream = FileInputStream(mFile)
            var idx = 0
            var read = 0
//                val finished = false
//            themedWallpaperFileOffset = -1
            while (stream.read(bytes).let { read = it; it != -1 }) {
                val previousPosition = currentPosition
                var start = 0
                for (a in 0 until read) {
                    if (bytes[a] == '\n'.toByte()) {//not reads last line if there is no newline in last line
                        val len = a - start + 1
                        val line = String(bytes, start, len - 1, Charsets.UTF_8)
//                        if (line.startsWith("WPS")) {
//                            themedWallpaperFileOffset = currentPosition + len
//                            finished = true
//                            break
//                        } else {
                        if (line.indexOf('=').let { idx = it; it != 0 }) {

                            val key = line.substring(0, idx)
                            val param = line.run {
                                substring(idx + 1, if (elementAt(lastIndex) == '\r') lastIndex else lastIndex + 1)
                            }

                            var value: Int
                            value = if (param.isNotEmpty() && param[0] == '#') {
                                try {
                                    Color.parseColor(param)
                                } catch (ignore: Exception) {
                                    parseInt(param)
                                }

                            } else {
                                parseInt(param)
                            }
                            colors[key] = value
                        }
//                        }
                        start += len
                        currentPosition += len
                    }
                }
                if (previousPosition == currentPosition) {
                    break
                }
                stream.channel.position(currentPosition.toLong())
//                    if (finished) {
//                        break
//                    }
            }
        } catch (e: Throwable) {
//                FileLog.e(e)
        } finally {
            try {
                stream?.close()
            } catch (e: Exception) {
//                    FileLog.e(e)
            }

        }
        return colors
    }

    private fun getAssetFile(assetName: String, context: Context): File {
        val file = File(context.filesDir, assetName)
        var size: Long
        try {
            val stream = context.assets.open(assetName)
            size = stream.available().toLong()
            stream.close()
        } catch (e: Exception) {
            size = 0
//                FileLog.e(e)
        }

        if (!file.exists() || size != 0L && file.length() != size) {
            var inputStream: InputStream? = null
            try {
                inputStream = context.assets.open(assetName)
                copyFile(inputStream, file)
            } catch (e: Exception) {
//                    FileLog.e(e)
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close()
                    } catch (ignore: Exception) {

                    }

                }
            }
        }
        return file
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
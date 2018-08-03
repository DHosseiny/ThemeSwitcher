package ir.parsdroid.themeapp

import android.app.Activity
import android.app.ActivityManager
import android.graphics.Color
import android.os.Build
import ir.parsdroid.themeapp.Utilities.getAssetsDir
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*


/**
 * Zigzag Project
 * Created by Seyyed Davud Hosseiny on 5/15/2018.
 */

class ThemeEditor {


    companion object {

        @JvmStatic
        fun applyTheme(theme: ThemeInfo) {

            val editor = Preferences.getSharedPreferences().edit()
            editor.putString(Preferences.THEME, theme.name)
            editor.putBoolean(Preferences.IS_ASSET, theme.isAsset)
            editor.apply()

            if (theme.name == Theme.DEFAULT) {
                Theme.getInstance().resetTheme()
            } else Theme.getInstance().setTheme(theme, getThemeFileValues(theme))
        }

        @JvmStatic
        private fun getThemeFileValues(themeInfo: ThemeInfo): HashMap<String, Int> {

            val mFile = if (themeInfo.isAsset) {
                getAssetFile(themeInfo.name + ".attheme")
            } else {
//                getInternalDir()
                null
            }


            var stream: FileInputStream? = null
            val stringMap = HashMap<String, Int>()
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
                                        Utilities.parseInt(param)
                                    }

                                } else {
                                    Utilities.parseInt(param)
                                }
                                stringMap[key] = value
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
            return stringMap
        }

        private fun getAssetFile(assetName: String): File {
            val file = File(getAssetsDir(), assetName)
            var size: Long
            try {
                val stream = ApplicationLoader.applicationContext.assets.open(assetName)
                size = stream.available().toLong()
                stream.close()
            } catch (e: Exception) {
                size = 0
//                FileLog.e(e)
            }

            if (!file.exists() || size != 0L && file.length() != size) {
                var inputStream: InputStream? = null
                try {
                    inputStream = ApplicationLoader.applicationContext.assets.open(assetName)
                    Utilities.copyFile(inputStream, file)
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

        @JvmStatic
        fun checkTheme(activity: Activity) {

            val themeName = Preferences.getSharedPreferences().getString(Preferences.THEME, Theme.DEFAULT)

            if (themeName != Theme.DEFAULT) {

                val isAsset = Preferences.getSharedPreferences().getBoolean(Preferences.IS_ASSET, false)

                val themeInfo = ThemeInfo(themeName!!, isAsset)

                Theme.loadTheme(themeInfo, getThemeFileValues(themeInfo))
//                val overrideChatBackground = Preferences.getSharedPreferences().getBoolean(Preferences.OVERRIDE_BACKGROUND, false)
//                loadWallpaper(activity)
            }

            //must be after Theme.loadTheme() to load proper color
            changeStatusBarColor(activity)
            setTaskDescription(activity)

        }

        private fun setTaskDescription(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    activity.setTaskDescription(ActivityManager.TaskDescription(null, null, Theme.getColor(Theme.getInstance().colorPrimary) or -0x1000000))
                } catch (e: Exception) {
                    //
                }

            }
        }

        @JvmStatic
        fun changeStatusBarColor(activity: Activity) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = activity.window
                window.statusBarColor = Theme.getColor(Theme.getInstance().colorPrimaryDark)
            }
        }
    }


}


class ThemeInfo constructor(val name: String,
                            val isAsset: Boolean) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ThemeInfo

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

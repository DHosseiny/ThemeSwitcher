/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcher.core.theme

class Theme(val themeInfo: ThemeInfo = defaultThemeInfo,
            private val palette: Palette = defaultPalette) {

    companion object {

        const val colorPrimary: ColorKey = "colorPrimary"
        const val fabBackground: ColorKey = "fabBackground"
        const val textColorInverse: ColorKey = "textColorInverse"
        const val fabIconColor: ColorKey = "fabIconColor"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        return themeInfo == (other as Theme).themeInfo
    }

    override fun hashCode(): Int {
        return themeInfo.hashCode()
    }

    fun getColor(colorKey: ColorKey): Int {
        return palette.getColor(colorKey)
    }

}

typealias ColorKey = String
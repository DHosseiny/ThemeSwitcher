/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcher.core.theme

class Theme(val themeInfo: ThemeInfo = defaultThemeInfo,
            private val palette: Palette = defaultPalette) {

    companion object {

        val colorPrimary = ColorKey("colorPrimary")
        val fabBackground = ColorKey("fabBackground")
        val textColorInverse = ColorKey("fabIconColor")
        val fabIconColor = ColorKey("textColorInverse")
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

data class ColorKey internal constructor(internal val key: String)
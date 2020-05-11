/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcher.core.theme

class Palette(private val colors: Map<ColorKey, Int>) {

    fun getColor(colorKey: ColorKey): Int {
        return colors[colorKey] ?: defaultPalette.colors.getValue(colorKey)
    }
}
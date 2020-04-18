/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.theme

import davud.hosseini.themeswitcher.core.theme.Palette.Companion.colorPrimary
import davud.hosseini.themeswitcher.core.theme.Palette.Companion.fabBackground
import davud.hosseini.themeswitcher.core.theme.Palette.Companion.fabIconColor
import davud.hosseini.themeswitcher.core.theme.Palette.Companion.textColorInverse


@OptIn(ExperimentalStdlibApi::class)
val defaultPalette = Palette(mutableMapOf<String, Int>().apply {
    put(colorPrimary, 0xFF3f51b5.toInt())
    put(fabBackground, 0xFF009688.toInt())
    put(fabIconColor, 0xFFFFFFFF.toInt())
    put(textColorInverse, 0xFFFFFFFF.toInt())
})

class Palette(private val colors: Map<String, Int>) {

    fun getColor(key: String): Int {
        return colors[key] ?: defaultPalette.colors.getValue(key)
    }

    companion object {

        internal const val colorPrimary = "colorPrimary"
        internal const val fabBackground = "fabBackground"
        internal const val fabIconColor = "fabIconColor"
        internal const val textColorInverse = "textColorInverse"
    }
}
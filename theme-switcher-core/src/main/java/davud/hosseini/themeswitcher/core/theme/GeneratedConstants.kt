/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcher.core.theme

@OptIn(ExperimentalStdlibApi::class)
internal val defaultPalette = Palette(mutableMapOf<ColorKey, Int>().apply {
    put(Theme.colorPrimary, 0xFF3f51b5.toInt())
    put(Theme.fabBackground, 0xFF009688.toInt())
    put(Theme.fabIconColor, 0xFFFFFFFF.toInt())
    put(Theme.textColorInverse, 0xFFFFFFFF.toInt())
})

internal val predefinedThemesInfo = listOf(ThemeInfo(DEFAULT),
        ThemeInfo("night"),
        ThemeInfo("day"),
        ThemeInfo("darkblue"))//TODO: Generate names
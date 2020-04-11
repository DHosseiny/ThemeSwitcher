/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.theme

internal const val DEFAULT = "default"
internal val defaultThemeInfo = ThemeInfo(DEFAULT, true)
internal val predefinedThemesInfo = listOf(ThemeInfo(DEFAULT),
        ThemeInfo("night"),
        ThemeInfo("day"),
        ThemeInfo("darkblue"))//TODO: Generate names
internal const val ASSETS_SUBFOLDER: String = "themes"

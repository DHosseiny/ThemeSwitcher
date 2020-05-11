/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

@file:JvmName("Theme")

package davud.hosseini.themeswitcher

import androidx.databinding.ObservableField
import davud.hosseini.themeswitcher.core.theme.ColorKey
import davud.hosseini.themeswitcher.core.theme.Theme
import davud.hosseini.themeswitcher.core.theme.ThemeSwitcher

private val theme: Theme
    get() = ThemeSwitcher.theme

val colorPrimary: ObservableField<ColorKey> = ObservableField(Theme.colorPrimary)
val textColorInverse: ObservableField<ColorKey> = ObservableField(Theme.textColorInverse)
val fabBackground: ObservableField<ColorKey> = ObservableField(Theme.fabBackground)
val fabIconColor: ObservableField<ColorKey> = ObservableField(Theme.fabIconColor)

fun getColor(colorKey: ObservableField<ColorKey>): Int {
    return theme.getColor(colorKey.get()!!)
}
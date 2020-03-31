/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher

import android.app.Activity
import davud.hosseini.themeswitcher.core.theme.ThemeSwitcher

fun ThemeSwitcher.instantiate(activity: Activity) {
    instantiate(activity, BindingTheme)
}

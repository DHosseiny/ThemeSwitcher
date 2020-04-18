/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher

import android.app.Activity
import davud.hosseini.themeswitcher.core.theme.ThemeSwitcher

object DataBindingThemeSwitcher {
    fun setup(activity: Activity) {

        ThemeSwitcher.setup(activity)
        ThemeSwitcher.addThemeChangeListener {
            colorPrimary.notifyChange()
            textColorInverse.notifyChange()
            fabBackground.notifyChange()
            fabIconColor.notifyChange()
        }
    }
}
/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcher.core.preferences

import android.content.Context
import android.content.SharedPreferences
import davud.hosseini.themeswitcher.core.BuildConfig

internal class PreferencesImpl(context: Context) : Preferences() {

    override val preferences: SharedPreferences = getSharedPreferences(context)

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(getSharedPreferencesName(), Context.MODE_PRIVATE)
    }

    private fun getSharedPreferencesName() = BuildConfig.LIBRARY_PACKAGE_NAME + "_preferences"
}
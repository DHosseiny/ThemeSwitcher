package davud.hosseini.themeswitcher.core.preferences

import android.content.Context
import android.content.SharedPreferences
import davud.hosseini.themeswitcher.core.BuildConfig

internal class PreferencesImpl(context: Context) : Preferences() {

    override val preferences: SharedPreferences = getSharedPreferences(context)

    val theme: String by StringPrefProperty()
    val isAsset: Boolean by BooleanPrefProperty()
    val background: String by StringPrefProperty()
    val overrideBackground: Boolean by BooleanPrefProperty()

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(getSharedPreferencesName(), Context.MODE_PRIVATE)
    }

    private fun getSharedPreferencesName() = BuildConfig.LIBRARY_PACKAGE_NAME + "_preferences"
}
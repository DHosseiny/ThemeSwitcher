/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.preferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal abstract class Preferences {

    abstract val preferences: SharedPreferences

    var themeName: String by StringPrefProperty()
    var isAsset: Boolean by BooleanPrefProperty(true)
    var background: String by StringPrefProperty()
    var overrideBackground: Boolean by BooleanPrefProperty()

    protected class StringPrefProperty : ReadWriteProperty<Preferences, String> {

        override fun getValue(thisRef: Preferences, property: KProperty<*>): String {
            return thisRef.preferences.getString(property.name, "")!!
        }

        override fun setValue(thisRef: Preferences, property: KProperty<*>, value: String) {
            thisRef.preferences.edit().putString(property.name, value).apply()
        }
    }

    protected class BooleanPrefProperty(private val defaultValue: Boolean = false) : ReadWriteProperty<Preferences, Boolean> {

        override fun getValue(thisRef: Preferences, property: KProperty<*>): Boolean {
            return thisRef.preferences.getBoolean(property.name, defaultValue)
        }

        override fun setValue(thisRef: Preferences, property: KProperty<*>, value: Boolean) {
            thisRef.preferences.edit().putBoolean(property.name, value).apply()
        }
    }

}
package davud.hosseini.themeswitcher.core.preferences

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

internal abstract class Preferences {

    protected abstract val preferences: SharedPreferences

    protected class StringPrefProperty : ReadWriteProperty<Preferences, String> {

        override fun getValue(thisRef: Preferences, property: KProperty<*>): String {
            return thisRef.preferences.getString(property.name, "")!!
        }

        override fun setValue(thisRef: Preferences, property: KProperty<*>, value: String) {
            thisRef.preferences.edit().putString(property.name, value).apply()
        }
    }

    protected class BooleanPrefProperty : ReadWriteProperty<Preferences, Boolean> {

        override fun getValue(thisRef: Preferences, property: KProperty<*>): Boolean {
            return thisRef.preferences.getBoolean(property.name, false)
        }

        override fun setValue(thisRef: Preferences, property: KProperty<*>, value: Boolean) {
            thisRef.preferences.edit().putBoolean(property.name, value).apply()
        }
    }

}
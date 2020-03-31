/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.theme

data class ThemeInfo @JvmOverloads constructor(val name: String, val isAsset: Boolean = true) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ThemeInfo

        return name == other.name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

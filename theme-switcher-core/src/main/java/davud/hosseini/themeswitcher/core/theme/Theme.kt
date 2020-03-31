/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.theme

import androidx.annotation.ColorInt

val colorPrimaryColor = Color("colorPrimary", -0xc0ae4b)

abstract class Theme<Observable : ColorObservable<*>> {

    internal var themeInfo: ThemeInfo = defaultThemeInfo

    abstract val colorPrimary: Observable


    @OptIn(ExperimentalStdlibApi::class)
    internal val defaultColors: Map<String, Int> = buildMap<String, Int> {
        put(colorPrimaryColor.key, colorPrimaryColor.value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        return themeInfo == (other as Theme<*>).themeInfo
    }

    override fun hashCode(): Int {
        return themeInfo.hashCode()
    }

    fun setColorValues(colorValues: Map<String, Int>) {
//        val withDefault = colorValues.withDefault { defaultColors.getValue(it) }
        colorPrimary.setColorValue(colorValues.getValue(colorPrimary.color.key))
//        this.colorValues.putAll(colorValues)
    }

    fun resetToDefaults() {
        themeInfo = defaultThemeInfo
        setColorValues(defaultColors)
    }
}

fun getColor(colorObservable: ColorObservable<*>): Int {
    return colorObservable.color.value
}

//object ThemeJava : Theme<ColorJavaObservable>() {
//    override val colorPrimary: ColorJavaObservable = ColorJavaObservable(colorPrimaryColor)
//}

class Color internal constructor(val key: String, @ColorInt value: Int) {
    @ColorInt var value: Int = value
        internal set
}

//private infix fun String.to(colorValue: Int): Color = Color(this, colorValue)
//
//interface Observable<T> {
//    var value: T by Delegates.observable()
//    set(value) {
//        notifyObservers(this.value, value)
//    }
//
//    fun addObserver(colorObserver: T)
//
//    fun removeObserver(colorObserver: T)
//
//    fun notifyObservers(oldValue: T, newValue: T)
//}

interface ColorObservable<T : ColorObserver> {
    val color: Color

    fun setColorValue(value: Int) {
        color.value = value
        notifyObservers()
    }

    fun addObserver(colorObserver: T)

    fun removeObserver(colorObserver: T)

    fun notifyObservers()
}

interface ColorObserver {

    fun onColorChanged(color: Color)
}

//class ColorJavaObservable(override val color: Color) : ColorObservable<ColorJavaObserver> {
//
//    private val support = PropertyChangeSupport(this)
//
//    override fun addObserver(colorObserver: ColorJavaObserver) {
//        support.addPropertyChangeListener(colorObserver)
//    }
//
//    override fun removeObserver(colorObserver: ColorJavaObserver) {
//        support.removePropertyChangeListener(colorObserver)
//    }
//
//    override fun notifyObservers(newValue: Int) {
//        support.firePropertyChange(color.key, newValue as Any)
//    }
//}
//
//abstract class ColorJavaObserver : ColorObserver, PropertyChangeListener {
//
//    final override fun propertyChange(event: PropertyChangeEvent) {
//        onColorChanged(event.newValue as Color)
//    }
//}
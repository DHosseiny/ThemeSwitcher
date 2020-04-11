/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher.core.theme

import androidx.annotation.ColorInt

abstract class Theme<Observable : ColorObservable<*>>(palette: Palette = defaultPalette) {
    internal var palette: Palette = palette
        set(value) {
            field = value
            //notify Observables
            colorPrimary.notifyObservers()
            fabBackground.notifyObservers()
            fabIconColor.notifyObservers()
            textColorInverse.notifyObservers()
        }

    internal var themeInfo: ThemeInfo = defaultThemeInfo

    abstract val colorPrimary: Observable
    abstract val fabBackground: Observable
    abstract val textColorInverse: Observable
    abstract val fabIconColor: Observable


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        return themeInfo == (other as Theme<*>).themeInfo
    }

    override fun hashCode(): Int {
        return themeInfo.hashCode()
    }

    fun resetToDefaults() {
        themeInfo = defaultThemeInfo
        palette = defaultPalette
    }

    fun getColor(colorObservable: ColorObservable<*>): Int {
        return palette.getColor(colorObservable.colorName)
    }

    fun getColorObject(colorObservable: ColorObservable<*>): Color {
        return Color(colorObservable.colorName, palette.getColor(colorObservable.colorName))
    }
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
    val colorName: String

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
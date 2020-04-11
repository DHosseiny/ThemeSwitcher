/*
 * Created by Davud. ThemeApp project.
 */

@file:JvmName("Theme")

package davud.hosseini.themeswitcher

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import davud.hosseini.themeswitcher.BindingTheme.colorPrimary
import davud.hosseini.themeswitcher.BindingTheme.fabBackground
import davud.hosseini.themeswitcher.BindingTheme.fabIconColor
import davud.hosseini.themeswitcher.BindingTheme.textColorInverse
import davud.hosseini.themeswitcher.core.theme.*

val colorPrimary: ColorObservableField = colorPrimary
val textColorInverse: ColorObservableField = textColorInverse
val fabBackground: ColorObservableField = fabBackground
val fabIconColor: ColorObservableField = fabIconColor

object BindingTheme : Theme<ColorObservableField>() {

    override val colorPrimary = ColorObservableField(Palette.colorPrimary)
    override val textColorInverse = ColorObservableField(Palette.textColorInverse)
    override val fabBackground = ColorObservableField(Palette.fabBackground)
    override val fabIconColor = ColorObservableField(Palette.fabIconColor)
}

fun getColor(colorObservable: ColorObservable<*>): Int {
    return BindingTheme.getColor(colorObservable)
}

class ColorObservableField(override val colorName: String) : ColorObservable<ColorObserverField>, BaseObservable() {
    override fun addObserver(colorObserver: ColorObserverField) {
        addOnPropertyChangedCallback(colorObserver)
    }

    override fun removeObserver(colorObserver: ColorObserverField) {
        removeOnPropertyChangedCallback(colorObserver)
    }

    override fun notifyObservers() {
        notifyChange()
    }

}

open class ColorObserverField : ColorObserver, Observable.OnPropertyChangedCallback() {

    override fun onColorChanged(color: Color) {
        //Not Implemented
    }

    override fun onPropertyChanged(sender: Observable, propertyId: Int) {
        onColorChanged(BindingTheme.getColorObject(sender as ColorObservableField))
    }
}
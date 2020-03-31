/*
 * Created by Davud. ThemeApp project.
 */

@file:JvmName("Theme")

package davud.hosseini.themeswitcher

import androidx.databinding.BaseObservable
import androidx.databinding.Observable
import davud.hosseini.themeswitcher.BindingTheme.colorPrimary
import davud.hosseini.themeswitcher.core.theme.*

val colorPrimary: ColorObservableField = colorPrimary

object BindingTheme : Theme<ColorObservableField>() {

    override val colorPrimary = ColorObservableField(colorPrimaryColor)
}

fun getColor(colorObservable: ColorObservable<*>): Int {
    return davud.hosseini.themeswitcher.core.theme.getColor(colorObservable)
}

class ColorObservableField(override val color: Color) : ColorObservable<ColorObserverField>, BaseObservable() {
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

class ColorObserverField : ColorObserver, Observable.OnPropertyChangedCallback() {

    override fun onColorChanged(color: Color) {
        //Not Implemented
    }

    override fun onPropertyChanged(sender: Observable, propertyId: Int) {
        onColorChanged((sender as ColorObservableField).color)
    }
}
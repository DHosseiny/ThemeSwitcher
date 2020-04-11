/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeswitcher

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.databinding.BindingConversion
import davud.hosseini.themeswitcher.core.theme.ColorObservable


@BindingConversion
@ColorInt
fun stringToColorInt(colorObservable: ColorObservableField): Int {
    return BindingTheme.getColor(colorObservable)
}

@BindingConversion
fun stringToColorStateList(colorObservable: ColorObservable<*>): ColorStateList {
    return ColorStateList.valueOf(BindingTheme.getColor(colorObservable))
}
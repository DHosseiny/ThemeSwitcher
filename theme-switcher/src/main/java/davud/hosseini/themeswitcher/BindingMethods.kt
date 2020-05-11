/*
 * Created by DHosseiny. ThemeSwitcher project.
 */

package davud.hosseini.themeswitcher

import android.content.res.ColorStateList
import androidx.annotation.ColorInt
import androidx.databinding.BindingConversion
import androidx.databinding.ObservableField
import davud.hosseini.themeswitcher.core.theme.ColorKey


@BindingConversion
@ColorInt
fun stringToColorInt(colorObservable: ObservableField<ColorKey>): Int {
    return getColor(colorObservable)
}

@BindingConversion
fun stringToColorStateList(colorObservable: ObservableField<ColorKey>): ColorStateList {
    return ColorStateList.valueOf(getColor(colorObservable))
}
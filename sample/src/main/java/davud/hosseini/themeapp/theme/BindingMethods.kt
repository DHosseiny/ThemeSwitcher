package davud.hosseini.themeapp.theme

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter


@BindingAdapter("bind:navigationIconTint")
fun setNavigationIconTint(toolbar: Toolbar, colorKey: String) {
    // TODO: needs to check for old value?
    toolbar.overflowIcon?.setTint(Theme.getColor(colorKey))
}

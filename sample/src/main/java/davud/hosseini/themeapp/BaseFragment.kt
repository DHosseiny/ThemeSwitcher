/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeapp

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

open class BaseFragment<B : ViewDataBinding> : Fragment() {

    lateinit var binding: B


    private val onThemeChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

//            if (propertyId == BR.theme) {
            onThemeChanged()
//            }
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        view.setBackgroundColor(Theme.getColor(Theme.windowBackground))
//        Theme.getInstance().addOnPropertyChangedCallback(onThemeChangedCallback)
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
//        Theme.getInstance().removeOnPropertyChangedCallback(onThemeChangedCallback)
    }

    private fun onThemeChanged() {
//        view?.setBackgroundColor(Theme.getColor(Theme.windowBackground))
    }

}
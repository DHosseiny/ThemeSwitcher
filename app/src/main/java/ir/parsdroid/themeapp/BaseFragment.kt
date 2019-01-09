package ir.parsdroid.themeapp

import android.content.Context
import android.databinding.Observable
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.View
import ir.parsdroid.themeapp.theme.Theme

open class BaseFragment<B : ViewDataBinding> : Fragment() {

    lateinit var interactionListener: InteractionListener
    lateinit var binding: B


    private val onThemeChangedCallback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

//            if (propertyId == BR.theme) {
            onThemeChanged()
//            }
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        interactionListener = context as InteractionListener
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.setBackgroundColor(Theme.getColor(Theme.windowBackground))
        view.isClickable = true

        Theme.getInstance().addOnPropertyChangedCallback(onThemeChangedCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Theme.getInstance().removeOnPropertyChangedCallback(onThemeChangedCallback)
    }

    private fun onThemeChanged() {
        view?.setBackgroundColor(Theme.getColor(Theme.windowBackground))
    }

}
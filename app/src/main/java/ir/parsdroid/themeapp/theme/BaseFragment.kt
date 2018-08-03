package ir.parsdroid.themeapp.theme

import android.content.Context
import android.support.v4.app.Fragment
import ir.parsdroid.themeapp.ThemeInfo

open class BaseFragment : Fragment() {

    lateinit var interactionListener: InteractionListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        interactionListener = context as InteractionListener
    }
}

interface InteractionListener {
    fun onThemeSelected(theme: ThemeInfo)

}

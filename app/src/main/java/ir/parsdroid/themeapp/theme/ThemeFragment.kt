package ir.parsdroid.themeapp.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.parsdroid.themeapp.ThemeAgent
import ir.parsdroid.themeapp.databinding.FragmentThemeBinding

/**
 * Created by Davud. ThemeApp project.
 */
class ThemeFragment : BaseFragment() {

    private lateinit var adapter: ThemeAdapter
    private lateinit var binding: FragmentThemeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val (themes, selectedThemePosition) = ThemeAgent.getThemes()

        adapter = ThemeAdapter(themes, selectedThemePosition, BaseAdapter.OnItemClickListener {
            interactionListener.onThemeSelected(it)
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentThemeBinding.inflate(inflater, container, false)

        binding.recyclerTheme.adapter = adapter

        return binding.root
    }
}

package ir.parsdroid.themeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.parsdroid.themeapp.databinding.FragmentThemeBinding
import ir.parsdroid.themeapp.theme.ThemeAgent

/**
 * Created by Davud. ThemeApp project.
 */
class ThemeFragment : BaseFragment<FragmentThemeBinding>() {

    private lateinit var adapter: ThemeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val (themes, selectedThemePosition) = ThemeAgent.getThemes()

        adapter = ThemeAdapter(themes, selectedThemePosition, BaseAdapter.OnItemClickListener {
            interactionListener.onThemeSelected(it)
            adapter.setSelectedThemePosition(themes.indexOf(it))
            adapter.notifyDataSetChanged()
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentThemeBinding.inflate(inflater, container, false)

        binding.recyclerTheme.adapter = adapter

        return binding.root
    }
}

/*
 * Created by Davud. ThemeApp project.
 */

package davud.hosseini.themeapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import davud.hosseini.themeapp.databinding.FragmentThemeBinding
import davud.hosseini.themeswitcher.core.theme.ThemeSwitcher

/**
 * Created by Davud. ThemeApp project.
 */
class ThemeFragment : BaseFragment<FragmentThemeBinding>() {

    private lateinit var adapter: ThemeAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val (themes, selectedThemePosition) = ThemeSwitcher.getThemes(requireContext())

        adapter = ThemeAdapter(themes, selectedThemePosition, BaseAdapter.OnItemClickListener {
            ThemeSwitcher.changeTheme(it, requireActivity())
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

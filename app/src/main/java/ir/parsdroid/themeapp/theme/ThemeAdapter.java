package ir.parsdroid.themeapp.theme;

import android.support.annotation.NonNull;

import java.util.List;

import ir.parsdroid.themeapp.R;
import ir.parsdroid.themeapp.ThemeInfo;
import ir.parsdroid.themeapp.databinding.ItemThemeBinding;

/**
 * Created by Davud. ThemeApp project.
 */
class ThemeAdapter extends BaseAdapter<ItemThemeBinding> {


    private List<ThemeInfo> themes;
    private int selectedThemePosition;
    private final BaseAdapter.OnItemClickListener<ThemeInfo> clickListener;

    public ThemeAdapter(List<ThemeInfo> themes, int selectedThemePosition,
                        BaseAdapter.OnItemClickListener<ThemeInfo> clickListener) {

        this.themes = themes;
        this.selectedThemePosition = selectedThemePosition;
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<ItemThemeBinding> holder, int position) {

        ThemeInfo themeInfo = themes.get(position);

        holder.binding.textTheme.setText(themeInfo.getName());
        holder.binding.radioTheme.setChecked(selectedThemePosition == position);

        holder.bind();
        holder.itemView.setOnClickListener(view -> {

            clickListener.onItemClick(themeInfo);
        });
    }


    @Override
    public int getItemCount() {
        return themes.size();
    }

    @Override
    int getLayoutForPosition(int position) {
        return R.layout.item_theme;
    }
}

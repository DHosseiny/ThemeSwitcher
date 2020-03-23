package davud.hosseini.themeapp;

import androidx.annotation.NonNull;

import java.util.List;

import davud.hosseini.themeapp.databinding.ItemThemeBinding;
import davud.hosseini.themeapp.theme.ThemeInfo;

/**
 * Created by Davud. ThemeApp project.
 */
class ThemeAdapter extends BaseAdapter<ItemThemeBinding> {


    private final List<ThemeInfo> themes;
    private int selectedThemePosition;
    private final BaseAdapter.OnItemClickListener<ThemeInfo> clickListener;

    public ThemeAdapter(List<ThemeInfo> themes, int selectedThemePosition,
                        BaseAdapter.OnItemClickListener<ThemeInfo> clickListener) {

        this.themes = themes;
        this.selectedThemePosition = selectedThemePosition;
        this.clickListener = clickListener;
    }

    public void setSelectedThemePosition(int selectedThemePosition) {
        this.selectedThemePosition = selectedThemePosition;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<ItemThemeBinding> holder, int position) {

        ThemeInfo themeInfo = themes.get(position);


        holder.binding.textTheme.setText(themeInfo.getName());
        holder.binding.radioTheme.setChecked(selectedThemePosition == position);

        holder.bind();
        holder.itemView.setOnClickListener(view -> clickListener.onItemClick(themeInfo));
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

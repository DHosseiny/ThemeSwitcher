package ir.parsdroid.themeapp.theme;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import ir.parsdroid.themeapp.Theme;

/**
 * Zigzag Project
 * Created by Seyyed Davud Hosseiny on 7/3/2017.
 */

public abstract class BaseAdapter<B extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseAdapter.ViewHolder<B>> {

    public BaseAdapter() {
    }

    @NonNull
    public ViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent,
                                            int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        B binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new ViewHolder<>(binding);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutForPosition(position);
    }

    abstract int getLayoutForPosition(int position);

    public interface OnItemClickListener<E> {
        void onItemClick(E item);
    }

    public interface OnItemLongClickListener<E> {
        void onItemLongClick(E item);
    }

    public static class ViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
        public final B binding;

        public ViewHolder(B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind() {
            binding.setVariable(BR.theme, Theme.getInstance());
            binding.executePendingBindings();
        }
    }
}

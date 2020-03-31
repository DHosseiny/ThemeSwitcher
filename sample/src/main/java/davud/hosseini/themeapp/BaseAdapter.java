package davud.hosseini.themeapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/*
 * Created by Davud. ThemeApp project.
 */

public abstract class BaseAdapter<B extends ViewDataBinding>
        extends RecyclerView.Adapter<BaseAdapter.ViewHolder<B>> {

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

        ViewHolder(B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind() {
//            binding.setVariable(BR.theme, Theme.getInstance());
            binding.executePendingBindings();
        }
    }
}

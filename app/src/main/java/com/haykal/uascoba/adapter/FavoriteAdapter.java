package com.haykal.uascoba.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haykal.uascoba.databinding.ItemEndemicBinding;
import com.haykal.uascoba.model.FavoriteEntity;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private List<FavoriteEntity> favoriteList = new ArrayList<>();
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(FavoriteEntity favorite);
    }

    public FavoriteAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setFavoriteList(List<FavoriteEntity> favoriteList) {
        this.favoriteList = favoriteList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEndemicBinding binding = ItemEndemicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(favoriteList.get(position));
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemEndemicBinding binding;

        public ViewHolder(ItemEndemicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(FavoriteEntity favorite) {
            binding.tvName.setText(favorite.getNama());
            Glide.with(itemView.getContext())
                    .load(favorite.getFoto())
                    .into(binding.ivEndemic);
            itemView.setOnClickListener(v -> listener.onItemClick(favorite));
        }
    }
}

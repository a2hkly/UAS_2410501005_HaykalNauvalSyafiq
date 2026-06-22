package com.haykal.uascoba.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.haykal.uascoba.R;
import com.haykal.uascoba.databinding.ItemEndemicBinding;
import com.haykal.uascoba.model.EndemicEntity;

import java.util.ArrayList;
import java.util.List;

public class EndemicAdapter extends RecyclerView.Adapter<EndemicAdapter.ViewHolder> {

    private List<EndemicEntity> endemicList = new ArrayList<>();
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(EndemicEntity endemic);
    }

    public EndemicAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setEndemicList(List<EndemicEntity> endemicList) {
        this.endemicList = endemicList;
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
        holder.bind(endemicList.get(position));
    }

    @Override
    public int getItemCount() {
        return endemicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemEndemicBinding binding;

        public ViewHolder(ItemEndemicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(EndemicEntity endemic) {
            binding.tvName.setText(endemic.getNama());
            Glide.with(itemView.getContext())
                    .load(endemic.getFoto())
                    .placeholder(R.drawable.baseline_broken_image_24) // Gambar saat loading
                    .error(R.drawable.baseline_broken_image_24)    // Gambar jika error/tidak muncul
                    .into(binding.ivEndemic);
            itemView.setOnClickListener(v -> listener.onItemClick(endemic));
        }
    }
}

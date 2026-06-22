package com.haykal.uascoba.ui.favorit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.haykal.uascoba.adapter.FavoriteAdapter;
import com.haykal.uascoba.databinding.ActivityFavoritBinding;
import com.haykal.uascoba.repository.EndemicRepository;
import com.haykal.uascoba.ui.detail.DetailActivity;

public class FavoritActivity extends AppCompatActivity {

    private ActivityFavoritBinding binding;
    private EndemicRepository repository;
    private FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        repository = new EndemicRepository(getApplication());
        setupRecyclerView();
        loadFavorites();
    }

    private void setupRecyclerView() {
        adapter = new FavoriteAdapter(favorite -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("ENDEMIC_ID", favorite.getId());
            intent.putExtra("ENDEMIC_NAME", favorite.getNama());
            startActivity(intent);
        });
        binding.rvFavorite.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvFavorite.setAdapter(adapter);
    }

    private void loadFavorites() {
        repository.getAllFavorites().observe(this, favorites -> {
            adapter.setFavoriteList(favorites);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

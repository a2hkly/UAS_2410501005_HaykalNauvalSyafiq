package com.haykal.uascoba.ui.detail;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.haykal.uascoba.R;
import com.haykal.uascoba.databinding.ActivityDetailBinding;
import com.haykal.uascoba.model.EndemicEntity;
import com.haykal.uascoba.model.FavoriteEntity;
import com.haykal.uascoba.repository.EndemicRepository;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private EndemicRepository repository;
    private String endemicId;
    private EndemicEntity currentEndemic;
    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        repository = new EndemicRepository(getApplication());
        endemicId = getIntent().getStringExtra("ENDEMIC_ID");

        loadData();
    }

    private void loadData() {
        // Since we don't have a direct "getById" for EndemicEntity in DAO yet, 
        // we could add it or search from list. For simplicity, let's assume we can add it.
        // Or we can pass the whole object. Passing ID is better.
        
        // I'll add getEndemicById to Dao/Repository if needed, 
        // but for now let's use search with ID which is unique.
        
        repository.searchEndemic(getIntent().getStringExtra("ENDEMIC_NAME")).observe(this, list -> {
            if (list != null && !list.isEmpty()) {
                for (EndemicEntity entity : list) {
                    if (entity.getId().equals(endemicId)) {
                        currentEndemic = entity;
                        displayData(entity);
                        checkFavoriteStatus();
                        break;
                    }
                }
            }
        });
    }

    private void displayData(EndemicEntity endemic) {
        binding.tvName.setText(endemic.getNama());
        binding.tvLatin.setText(endemic.getNama_latin());
        binding.tvDescription.setText(endemic.getDeskripsi());
        binding.tvOrigin.setText("Asal: " + endemic.getAsal());
        Glide.with(this).load(endemic.getFoto()).into(binding.ivDetail);
        getSupportActionBar().setTitle(endemic.getNama());
    }

    private void checkFavoriteStatus() {
        repository.getFavoriteById(endemicId, favorite -> {
            isFavorite = (favorite != null);
            invalidateOptionsMenu();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        MenuItem favoriteItem = menu.findItem(R.id.action_favorite);
        if (isFavorite) {
            favoriteItem.setIcon(R.drawable.ic_favorite);
        } else {
            favoriteItem.setIcon(R.drawable.ic_favorite_border);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_favorite) {
            toggleFavorite();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleFavorite() {
        if (currentEndemic == null) return;

        FavoriteEntity favorite = new FavoriteEntity(
                currentEndemic.getId(),
                currentEndemic.getTipe(),
                currentEndemic.getNama(),
                currentEndemic.getNama_latin(),
                currentEndemic.getFamili(),
                currentEndemic.getGenus(),
                currentEndemic.getDeskripsi(),
                currentEndemic.getAsal(),
                currentEndemic.getSebaran(),
                currentEndemic.getFoto(),
                currentEndemic.getSumber_foto(),
                currentEndemic.getVidio(),
                currentEndemic.getSumber_vidio(),
                currentEndemic.getStatus()
        );

        if (isFavorite) {
            repository.deleteFavorite(favorite);
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        } else {
            repository.insertFavorite(favorite);
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
        isFavorite = !isFavorite;
        invalidateOptionsMenu();
    }
}

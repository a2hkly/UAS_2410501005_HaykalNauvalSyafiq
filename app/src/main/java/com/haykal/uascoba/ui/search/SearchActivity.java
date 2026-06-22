package com.haykal.uascoba.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;

import com.haykal.uascoba.adapter.EndemicAdapter;
import com.haykal.uascoba.databinding.ActivitySearchBinding;
import com.haykal.uascoba.repository.EndemicRepository;
import com.haykal.uascoba.ui.detail.DetailActivity;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private EndemicRepository repository;
    private EndemicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        repository = new EndemicRepository(getApplication());
        setupRecyclerView();
        setupSearchView();
    }

    private void setupRecyclerView() {
        adapter = new EndemicAdapter(endemic -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("ENDEMIC_ID", endemic.getId());
            intent.putExtra("ENDEMIC_NAME", endemic.getNama());
            startActivity(intent);
        });
        binding.rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvSearch.setAdapter(adapter);
    }

    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });
    }

    private void search(String query) {
        repository.searchEndemic(query).observe(this, endemicEntities -> {
            adapter.setEndemicList(endemicEntities);
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

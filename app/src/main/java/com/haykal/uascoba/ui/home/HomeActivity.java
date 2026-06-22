package com.haykal.uascoba.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.haykal.uascoba.R;
import com.haykal.uascoba.databinding.ActivityHomeBinding;
import com.haykal.uascoba.ui.favorit.FavoritActivity;
import com.haykal.uascoba.ui.search.SearchActivity;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.toolbar.setNavigationOnClickListener(v -> showProfileDialog());

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_hewan) {
                loadFragment(EndemicFragment.newInstance("Hewan"));
                return true;
            } else if (id == R.id.navigation_tumbuhan) {
                loadFragment(EndemicFragment.newInstance("Tumbuhan"));
                return true;
            }
            return false;
        });

        // Default fragment
        if (savedInstanceState == null) {
            binding.bottomNavigation.setSelectedItemId(R.id.navigation_hewan);
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void showProfileDialog() {
        new MaterialAlertDialogBuilder(this)
                .setView(R.layout.dialog_profile)
                .setPositiveButton("Close", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_search) {
            startActivity(new Intent(this, SearchActivity.class));
            return true;
        } else if (id == R.id.action_favorite) {
            startActivity(new Intent(this, FavoritActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

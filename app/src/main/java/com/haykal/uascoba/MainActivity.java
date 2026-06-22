package com.haykal.uascoba;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.haykal.uascoba.databinding.ActivityMainBinding;
import com.haykal.uascoba.repository.EndemicRepository;
import com.haykal.uascoba.ui.home.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private EndemicRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new EndemicRepository(getApplication());

        checkData();
    }

    private void checkData() {
        repository.getCount(count -> {
            if (count > 0) {
                runOnUiThread(this::navigateToHome);
            } else {
                runOnUiThread(this::fetchData);
            }
        });
    }

    private void fetchData() {
        repository.fetchDataAndSave(new EndemicRepository.OnDataLoadedListener() {
            @Override
            public void onDataLoaded() {
                runOnUiThread(() -> navigateToHome());
            }

            @Override
            public void onError(String message) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                    // Fallback to home even if error, or show retry button
                    navigateToHome();
                });
            }
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

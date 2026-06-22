package com.haykal.uascoba.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.haykal.uascoba.api.ApiClient;
import com.haykal.uascoba.api.ApiService;
import com.haykal.uascoba.database.AppDatabase;
import com.haykal.uascoba.database.EndemicDao;
import com.haykal.uascoba.model.EndemicEntity;
import com.haykal.uascoba.model.FavoriteEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndemicRepository {
    private final EndemicDao endemicDao;
    private final ApiService apiService;
    private final ExecutorService executorService;

    public EndemicRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        endemicDao = db.endemicDao();
        apiService = ApiClient.getApiService();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void fetchDataAndSave(OnDataLoadedListener listener) {
        apiService.getEndemicData().enqueue(new Callback<List<EndemicEntity>>() {
            @Override
            public void onResponse(Call<List<EndemicEntity>> call, Response<List<EndemicEntity>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    executorService.execute(() -> {
                        endemicDao.insertAll(response.body());
                        listener.onDataLoaded();
                    });
                } else {
                    listener.onError("Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<List<EndemicEntity>> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    public LiveData<List<EndemicEntity>> getEndemicByType(String type) {
        return endemicDao.getEndemicByType(type);
    }

    public LiveData<List<EndemicEntity>> searchEndemic(String query) {
        return endemicDao.searchEndemic(query);
    }

    public void insertFavorite(FavoriteEntity favorite) {
        executorService.execute(() -> endemicDao.insertFavorite(favorite));
    }

    public void deleteFavorite(FavoriteEntity favorite) {
        executorService.execute(() -> endemicDao.deleteFavorite(favorite));
    }

    public LiveData<List<FavoriteEntity>> getAllFavorites() {
        return endemicDao.getAllFavorites();
    }

    public void getFavoriteById(String id, OnFavoriteLoadedListener listener) {
        executorService.execute(() -> {
            FavoriteEntity favorite = endemicDao.getFavoriteById(id);
            listener.onFavoriteLoaded(favorite);
        });
    }

    public void getCount(OnCountLoadedListener listener) {
        executorService.execute(() -> {
            int count = endemicDao.getCount();
            listener.onCountLoaded(count);
        });
    }

    public interface OnDataLoadedListener {
        void onDataLoaded();
        void onError(String message);
    }

    public interface OnFavoriteLoadedListener {
        void onFavoriteLoaded(FavoriteEntity favorite);
    }

    public interface OnCountLoadedListener {
        void onCountLoaded(int count);
    }
}

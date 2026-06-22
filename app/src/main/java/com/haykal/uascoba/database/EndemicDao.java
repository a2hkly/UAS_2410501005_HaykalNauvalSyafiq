package com.haykal.uascoba.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.haykal.uascoba.model.EndemicEntity;
import com.haykal.uascoba.model.FavoriteEntity;

import java.util.List;

@Dao
public interface EndemicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<EndemicEntity> endemicList);

    @Query("SELECT * FROM endemik WHERE tipe = :type")
    LiveData<List<EndemicEntity>> getEndemicByType(String type);

    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :query || '%'")
    LiveData<List<EndemicEntity>> searchEndemic(String query);

    @Query("SELECT COUNT(*) FROM endemik")
    int getCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteEntity favorite);

    @Delete
    void deleteFavorite(FavoriteEntity favorite);

    @Query("SELECT * FROM favorit")
    LiveData<List<FavoriteEntity>> getAllFavorites();

    @Query("SELECT * FROM favorit WHERE id = :id")
    FavoriteEntity getFavoriteById(String id);
}

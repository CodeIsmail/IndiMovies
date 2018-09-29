package com.idealorb.indimovies.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert
    void insert(FavoriteMovie movie);

    @Query("DELETE FROM favorite_movie")
    void deleteAll();

    @Delete
    void delete(FavoriteMovie movie);

    @Query("SELECT * from favorite_movie ORDER BY movieId ASC")
    LiveData<List<FavoriteMovie>> getAllMovies();
}

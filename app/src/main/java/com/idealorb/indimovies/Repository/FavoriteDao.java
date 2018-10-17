package com.idealorb.indimovies.Repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.idealorb.indimovies.model.Movie;

import java.util.List;

@Dao
public interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * from favorite_movie ORDER BY id ASC")
    LiveData<List<Movie>> getAllMovies();
}

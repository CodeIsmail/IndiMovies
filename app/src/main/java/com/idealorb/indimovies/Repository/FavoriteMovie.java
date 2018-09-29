package com.idealorb.indimovies.Repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorite_movie")
public class FavoriteMovie {

    @PrimaryKey
    @NonNull
    int movieId;
    String title;
    String poster;
    String synopsos;
    String rating;
    String releaseDate;
}

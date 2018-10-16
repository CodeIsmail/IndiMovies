package com.idealorb.indimovies.Repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.idealorb.indimovies.model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {
    public abstract  FavoriteDao favoriteDao();

    private static volatile AppDb INSTANCE;

    static AppDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDb.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDb.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

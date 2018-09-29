package com.idealorb.indimovies.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MovieReository {
    private FavoriteDao favoriteDao;
    private LiveData<List<FavoriteMovie>> movies;

    public MovieReository(Application application) {
        AppDb db = AppDb.getDatabase(application);
        favoriteDao = db.favoriteDao();
        movies = favoriteDao.getAllMovies();
    }

    LiveData<List<FavoriteMovie>> getAllMovies() {
        return movies;
    }


    public void delete(FavoriteMovie movie){
        favoriteDao.delete(movie);
    }
    public void insert (FavoriteMovie movie) {
        new insertAsyncTask(favoriteDao).execute(movie);
    }

    private static class insertAsyncTask extends AsyncTask<FavoriteMovie, Void, Void> {

        private FavoriteDao mAsyncTaskDao;

        insertAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final FavoriteMovie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
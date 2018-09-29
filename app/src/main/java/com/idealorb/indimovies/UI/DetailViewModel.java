package com.idealorb.indimovies.UI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.idealorb.indimovies.Repository.FavoriteMovie;
import com.idealorb.indimovies.Repository.MovieReository;

public class DetailViewModel extends AndroidViewModel {

    private MovieReository mRepository;


    public DetailViewModel (Application application) {
        super(application);
        mRepository = new MovieReository(application);
    }
    public void insert(FavoriteMovie movie) { mRepository.insert(movie); }

    public void delete(FavoriteMovie movie){
        mRepository.delete(movie);
    }
}

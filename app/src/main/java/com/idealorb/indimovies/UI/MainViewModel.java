package com.idealorb.indimovies.UI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.idealorb.indimovies.Repository.MovieReository;
import com.idealorb.indimovies.model.Movie;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<Movie>> movies ;
    private MovieReository repo;


    public MainViewModel(Application application){
        super(application);
        repo = new MovieReository(application);

    }

    public LiveData<List<Movie>> getMovies(int option){

        if (option != 2){
            movies = repo.loadRemoteMovies(option);
        }else{
            movies = repo.loadLocalMovies();
        }
        return  movies;
    }

}

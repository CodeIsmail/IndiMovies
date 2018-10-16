package com.idealorb.indimovies.UI;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.idealorb.indimovies.Repository.MovieReository;
import com.idealorb.indimovies.model.Movie;
import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.Review;
import com.idealorb.indimovies.model.Trailer;

import java.util.List;

public class DetailViewModel extends AndroidViewModel {

    private MovieReository mRepository;
    private static final String TAG = MainViewModel.class.getSimpleName();

    public DetailViewModel (Application application) {
        super(application);
        mRepository = new MovieReository(application);
    }

    public LiveData<MovieDetailJsonUtil> getMovieDetails(int movieId){
        return mRepository.loadRemoteMovieDetails(movieId);
    }

    public LiveData<List<Review>> getMovieReviews(int movieId){
        return  mRepository.loadRemoteMovieReviews(movieId);
    }

    public LiveData<List<Trailer>> getMovieTrailers(int movieId){
        return mRepository.loadRemoteMovieTrailers(movieId);
    }
    public void insert(Movie movie) { mRepository.insert(movie); }

    public void delete(Movie movie){
        mRepository.delete(movie);
    }
}

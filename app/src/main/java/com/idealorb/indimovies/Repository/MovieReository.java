package com.idealorb.indimovies.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.idealorb.indimovies.BuildConfig;
import com.idealorb.indimovies.model.Movie;
import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.MovieJsonUtil;
import com.idealorb.indimovies.model.Review;
import com.idealorb.indimovies.model.ReviewJsonUtil;
import com.idealorb.indimovies.model.Trailer;
import com.idealorb.indimovies.model.TrailerJsonUtil;
import com.idealorb.indimovies.network.IMoviesdbApi;
import com.idealorb.indimovies.network.MoviesRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieReository {

    private static final String API_KEY = BuildConfig.ApiKey;
    private static final String TAG = MovieReository.class.getSimpleName();
    private FavoriteDao favoriteDao;
    private IMoviesdbApi moviesdbApi;
    private LiveData<List<Movie>> movies;
    private MutableLiveData<List<Movie>> remoteMovies;
    private MutableLiveData<MovieDetailJsonUtil> remoteMovieDetails;
    private MutableLiveData<List<Review>> remoteMovieReviews;
    private MutableLiveData<List<Trailer>> remoteMovieTrailers;

    public MovieReository(Application application) {
        moviesdbApi = MoviesRemoteDataSource.getRetrofitInstance()
                .create(IMoviesdbApi.class);
        AppDb db = AppDb.getDatabase(application);
        favoriteDao = db.favoriteDao();
        movies = favoriteDao.getAllMovies();
        remoteMovies = new MutableLiveData<>();
        remoteMovieDetails = new MutableLiveData<>();
        remoteMovieReviews = new MutableLiveData<>();
        remoteMovieTrailers = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> loadLocalMovies() {
        return movies;
    }


    public void removeFavoriteMovie(Movie movie) {
        Completable.fromAction(() -> favoriteDao.delete(movie))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "Movie removed from favorites"),
                        throwable -> Log.e(TAG, throwable.getMessage())
                );
    }

    public void saveFavoriteMovie(Movie movie) {
        Movie favorite = new Movie(movie.getId(), movie.getTitle(),
                movie.getVoteAverage(), movie.getPosterPath(), movie.getOverview(),
                movie.getReleaseDate(), true);
        Completable.fromAction(() -> favoriteDao.insert(favorite))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> Log.d(TAG, "New favorite movie saved!"),
                        throwable -> Log.e(TAG, throwable.getMessage()));
    }


    public LiveData<List<Movie>> loadRemoteMovies(int sortOption) {
        Single<MovieJsonUtil> retrofitCall;


        if (sortOption == 0) {
            retrofitCall = moviesdbApi
                    .getPopularMovies(API_KEY, "en-US", 1);
            asyncNetworkCall(retrofitCall);
        } else if (sortOption == 1) {
            retrofitCall = moviesdbApi
                    .getTopRatedMovies(API_KEY, "en-US", 1);
            asyncNetworkCall(retrofitCall);
        }
        return remoteMovies;
    }

    public LiveData<MovieDetailJsonUtil> loadRemoteMovieDetails(int movieId) {
        Single<MovieDetailJsonUtil> movieDetailObservable =
                moviesdbApi
                        .getMovieDetail(movieId, API_KEY, "en-US", "release_dates");

        movieDetailObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieDetailJsonUtil -> remoteMovieDetails.setValue(movieDetailJsonUtil),
                        throwable -> Log.e(TAG, throwable.getMessage())
                );
        return remoteMovieDetails;
    }

    public LiveData<List<Review>> loadRemoteMovieReviews(int movieId) {
        Single<ReviewJsonUtil> movieDetailObservable =
                moviesdbApi
                        .getMovieReviews(movieId, API_KEY, "en-US", 1);

        movieDetailObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(reviewJsonUtil -> remoteMovieReviews.setValue(reviewJsonUtil.getReviews()),
                        throwable -> Log.e(TAG, throwable.getMessage())
                );
        return remoteMovieReviews;
    }

    public LiveData<List<Trailer>> loadRemoteMovieTrailers(int movieId) {
        Single<TrailerJsonUtil> movieDetailObservable =
                moviesdbApi
                        .getMovieVideos(movieId, API_KEY, "en-US");

        movieDetailObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trailerJsonUtil -> remoteMovieTrailers.setValue(trailerJsonUtil.getTrailers()),
                        throwable -> Log.e(TAG, throwable.getMessage())
                );
        return remoteMovieTrailers;
    }

    private void asyncNetworkCall(Single<MovieJsonUtil> retrofitCall) {

        retrofitCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((movieJsonUtil, throwable) -> {
                    if (movieJsonUtil != null)
                        remoteMovies.setValue(movieJsonUtil.getMovies());

                    if (throwable != null)
                        Log.e(TAG, throwable.getMessage());
                });
    }

}
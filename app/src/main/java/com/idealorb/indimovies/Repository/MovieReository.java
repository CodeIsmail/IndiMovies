package com.idealorb.indimovies.Repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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


    public void delete(Movie movie) {
        favoriteDao.delete(movie);
    }

    public void insert(Movie movie) {
        new insertAsyncTask(favoriteDao).execute(movie);
    }

    private static class insertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private FavoriteDao mAsyncTaskDao;

        insertAsyncTask(FavoriteDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }


    public LiveData<List<Movie>> loadRemoteMovies(int sortOption) {
        Observable<MovieJsonUtil> retrofitCall;


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

    public LiveData<MovieDetailJsonUtil> loadRemoteMovieDetails(int movieId){
        Observable<MovieDetailJsonUtil> movieDetailObservable =
                moviesdbApi
                        .getMovieDetail(movieId, API_KEY, "en-US", "release_dates");

        movieDetailObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieDetailJsonUtil>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetailJsonUtil movieDetailJsonUtil) {
                        remoteMovieDetails.setValue(movieDetailJsonUtil);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return  remoteMovieDetails;
    }

    public LiveData<List<Review>> loadRemoteMovieReviews(int movieId){
        Observable<ReviewJsonUtil> movieDetailObservable =
                moviesdbApi
                        .getMovieReviews(movieId, API_KEY, "en-US", 1);

        movieDetailObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReviewJsonUtil>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ReviewJsonUtil movieDetailJsonUtil) {
                        remoteMovieReviews.setValue(movieDetailJsonUtil.getReviews());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return  remoteMovieReviews;
    }

    public LiveData<List<Trailer>> loadRemoteMovieTrailers(int movieId){
        Observable<TrailerJsonUtil> movieDetailObservable =
                moviesdbApi
                        .getMovieVideos(movieId, API_KEY, "en-US");

        movieDetailObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TrailerJsonUtil>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TrailerJsonUtil trailerJsonUtil) {
                        remoteMovieTrailers.setValue(trailerJsonUtil.getTrailers());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return  remoteMovieTrailers;
    }
    private void asyncNetworkCall(Observable<MovieJsonUtil> retrofitCall) {

        retrofitCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviesObserver());
    }

    private Observer<MovieJsonUtil> moviesObserver(){
        return new Observer<MovieJsonUtil>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieJsonUtil movieJsonUtil) {
                remoteMovies.setValue(movieJsonUtil.getMovies());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "Data successfully loaded!");
            }
        };
    }


}
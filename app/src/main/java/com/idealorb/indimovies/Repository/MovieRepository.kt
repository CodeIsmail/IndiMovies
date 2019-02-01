package com.idealorb.indimovies.Repository

import android.util.Log
import com.idealorb.indimovies.BuildConfig
import com.idealorb.indimovies.model.MainModel
import com.idealorb.indimovies.model.TvShow
import com.idealorb.indimovies.model.TvShowEntity
import com.idealorb.indimovies.services.ITvShowsdbApi
import com.idealorb.indimovies.services.MoviesRemoteDataSource
import kotlinx.coroutines.Deferred

class MovieRepository {
    private val moviesdbApi: ITvShowsdbApi = MoviesRemoteDataSource.service

    fun loadRemotePopularShows(): Deferred<TvShowEntity> {
        Log.d(TAG, "loadRemotePopularShows(): called")
        return moviesdbApi.getPopularShows(API_KEY, API_LANG, 1)
    }
    fun loadRemoteTopRatedShows(): Deferred<TvShowEntity> {
        Log.d(TAG, "loadRemotePopularShows(): called")
        return moviesdbApi.getTopRatedShows(API_KEY, API_LANG, 1)
    }
    fun loadRemoteTrendingShows(): Deferred<TvShowEntity> {
        Log.d(TAG, "loadRemotePopularShows(): called")
        return moviesdbApi.getTrendingShows(TRENDING_MEDIA_TYPE, TRENDING_TIME_WINDOW,
                API_KEY)
    }

    fun loadHeaders(): List<String>{
        Log.d(TAG, "loadRemotePopularShows(): called")
        return arrayListOf("Trending", "Popular", "Top Rated")
    }

    fun loadMainViewData(tvShows : List<List<TvShow?>?>): List<MainModel>{
        Log.d(TAG, "loadRemotePopularShows(): called")
        val mainModels = ArrayList<MainModel>()
        val size = loadHeaders().size
        for (i in 0 until size){
            mainModels.add(MainModel(loadHeaders()[i], tvShows[i]))
        }
        return mainModels
    }

    companion object {
        private const val API_KEY = BuildConfig.ApiKey
        private const val API_LANG = "en-US"
        private const val TRENDING_MEDIA_TYPE = "tv"
        private const val TRENDING_TIME_WINDOW = "week"
        private val TAG = MovieRepository::class.java.simpleName
    }

}
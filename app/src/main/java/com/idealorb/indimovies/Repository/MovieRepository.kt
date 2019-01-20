package com.idealorb.indimovies.Repository

import android.util.Log
import com.idealorb.indimovies.BuildConfig
import com.idealorb.indimovies.model.MainModel
import com.idealorb.indimovies.model.TVShowEntity
import com.idealorb.indimovies.model.TvShow
import com.idealorb.indimovies.network.IMoviesdbApi
import com.idealorb.indimovies.network.MoviesRemoteDataSource
import kotlinx.coroutines.Deferred
import kotlin.random.Random

class MovieRepository {
    private val moviesdbApi: IMoviesdbApi = MoviesRemoteDataSource.retrofitInstance
            .create(IMoviesdbApi::class.java)

    fun loadRemoteShows(): Deferred<TVShowEntity> {
        Log.d(TAG, "loadRemoteShows(): called")
        return moviesdbApi.getPopularShows(API_KEY, API_LANG, 1)
    }

    fun loadHeaders(): List<String>{
        Log.d(TAG, "loadRemoteShows(): called")
        return arrayListOf("Popular", "Adventure", " Comedy", "Action")
    }

    fun loadMainViewData(tvShows : List<TvShow?>?): List<MainModel>{
        Log.d(TAG, "loadRemoteShows(): called")
        val mainModels = ArrayList<MainModel>()
        val size = loadHeaders().size
        for (i in 0 until size){
            val random = Random.nextInt(size.minus(1))
            mainModels.add(MainModel(loadHeaders()[random], tvShows))
        }
        return mainModels
    }

    companion object {
        private const val API_KEY = BuildConfig.ApiKey
        private const val API_LANG = "en-US"
        private val TAG = MovieRepository::class.java.simpleName
    }

}
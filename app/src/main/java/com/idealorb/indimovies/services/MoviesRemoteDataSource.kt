package com.idealorb.indimovies.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesRemoteDataSource {

    val service: ITvShowsdbApi
    private val BASE_URL = "https://api.themoviedb.org/3/"

    init {
        val retrofitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        service = retrofitInstance.create(ITvShowsdbApi::class.java)
    }
}

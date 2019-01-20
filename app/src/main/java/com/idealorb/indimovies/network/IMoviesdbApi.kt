package com.idealorb.indimovies.network

import com.idealorb.indimovies.model.TVShowEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface IMoviesdbApi {

    @GET("tv/popular")
    fun getPopularShows(@Query("api_key") api_key: String,
                        @Query("language") lang: String,
                        @Query("page") pageCount: Int): Deferred<TVShowEntity>

    @GET("tv/top_rated")
    fun getTopRatedShows(@Query("api_key") api_key: String,
                         @Query("language") lang: String,
                         @Query("page") pageCount: Int): Deferred<TVShowEntity>


}

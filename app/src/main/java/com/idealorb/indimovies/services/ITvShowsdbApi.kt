package com.idealorb.indimovies.services

import com.idealorb.indimovies.model.TvShowEntity
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ITvShowsdbApi {

    @GET("tv/popular")
    fun getPopularShows(@Query("api_key") api_key: String,
                        @Query("language") lang: String,
                        @Query("page") pageCount: Int): Deferred<TvShowEntity>

    @GET("tv/top_rated")
    fun getTopRatedShows(@Query("api_key") api_key: String,
                         @Query("language") lang: String,
                         @Query("page") pageCount: Int): Deferred<TvShowEntity>

    @GET("trending/{media_type}/{time_window}")
    fun getTrendingShows(@Path("media_type") mediaType: String,
                         @Path("time_window") timeWindow: String,
                         @Query("api_key") api_key: String) : Deferred<TvShowEntity>
}

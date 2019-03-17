package com.idealorb.tracketv.services

import com.idealorb.tracketv.model.TvShowDetailEntity
import com.idealorb.tracketv.model.TvShowEntity
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
                         @Query("api_key") api_key: String,
                         @Query("page") pageCount: Int): Deferred<TvShowEntity>

    @GET("tv/{tv_id}")
    fun getTvShow(@Path("tv_id") showId: Int,
                  @Query("api_key") api_key: String,
                  @Query("language") lang: String,
                  @Query("append_to_response") moreDataRequest: String): Deferred<TvShowDetailEntity>

    @GET("tv/{tv_id}/season/{season_number}")
    fun getTvShowSeason(@Path("tv_id") showId: Int,
                        @Path("season_number") seasonNumber: Int,
                        @Query("api_key") api_key: String,
                        @Query("language") lang: String,
                        @Query("append_to_response") moreDataRequest: String): Deferred<TvShowEntity>
}

package dev.codeismail.data.service

import dev.codeismail.data.model.TvEntity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object TvShowService {

    val service: ITvShowApi
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    init {
        val retrofitInstance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofitInstance.create(ITvShowApi::class.java)
    }
}

interface ITvShowApi {

    @GET("tv/popular")
    suspend fun getPopularEndpoint(@Query("api_key") api_key: String,
                                   @Query("language") lang: String,
                                   @Query("page") pageCount: Int): TvEntity

    @GET("tv/top_rated")
    suspend fun getTopRatedEndpoint(@Query("api_key") api_key: String,
                                    @Query("language") lang: String,
                                    @Query("page") pageCount: Int): Response<TvEntity>

    @GET("tv/latest")
    suspend fun getLatestEndpoint(@Query("api_key") api_key: String,
                                  @Query("language") lang: String): Response<TvEntity>

    @GET("trending/{media_type}/{time_window}")
    suspend fun getTrendingEndpoint(@Path("media_type") mediaType: String,
                                    @Path("time_window") timeWindow: String,
                                    @Query("api_key") api_key: String,
                                    @Query("page") pageCount: Int): Response<TvEntity>

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getTvShowSeasonEndpoint(@Path("tv_id") showId: Int,
                                        @Path("season_number") seasonNumber: Int,
                                        @Query("api_key") api_key: String,
                                        @Query("language") lang: String,
                                        @Query("append_to_response") moreDataRequest: String): Response<TvEntity>

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarTvShowsEndpoint(@Path("tv_id") showId: Int,
                                          @Query("api_key") api_key: String,
                                          @Query("language") lang: String,
                                          @Query("page") pageCount: Int): Response<TvEntity>

    @GET("tv/{tv_id}/recommendations")
    suspend fun getRecommendationEndpoint(@Path("tv_id") showId: Int,
                                          @Query("api_key") api_key: String,
                                          @Query("language") lang: String,
                                          @Query("page") pageCount: Int): Response<TvEntity>
}

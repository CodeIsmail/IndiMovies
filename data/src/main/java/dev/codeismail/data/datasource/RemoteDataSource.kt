package dev.codeismail.data.datasource

import dev.codeismail.data.BuildConfig
import dev.codeismail.data.model.Tv
import dev.codeismail.data.service.ITvShowApi

class RemoteDataSource(private val apiService: ITvShowApi) {

    suspend fun loadPopular(): List<Tv>{
        val popular = apiService
                .getPopularEndpoint(API_KEY, API_LANG,
                        1).results.toTypedArray()
        val topRated = apiService
                .getTopRatedEndpoint(API_KEY, API_LANG,
                        1)
                .results.toTypedArray()
        val trending = apiService.getTrendingEndpoint(
                MEDIA_TYPE, TIME_WINDOW, API_KEY, 1
        ).results.toTypedArray()

        return setOf(*trending, *popular, *topRated).toList()
    }

    suspend fun loadRecommended(tvId: Int): List<Tv>{
        val similarTv = apiService.getSimilarTvShowsEndpoint(tvId,
                API_KEY, API_LANG,
                1)
                .results.toTypedArray()
        val recommendTv = apiService.getRecommendationEndpoint(tvId,
                API_KEY, API_LANG,
                1)
                .results.toTypedArray()
        return setOf(*recommendTv, *similarTv).toList()

    }



    companion object {
        private val API_KEY = BuildConfig.API_KEY
        private const val API_LANG = "en-US"
        private const val MEDIA_TYPE = "tv"
        private const val TIME_WINDOW = "week"
        private const val APPEND_TO_RESPONSE = "Seasons,similar,content_ratings,reviews"
        private val TAG = RemoteDataSource::class.java.simpleName
    }
}
package dev.codeismail.data.datasource

import dev.codeismail.data.BuildConfig
import dev.codeismail.data.model.Tv
import dev.codeismail.data.service.ITvShowApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

class RemoteDataSource(private val apiService: ITvShowApi) {

    suspend fun loadPopular(): Flow<Tv> {
        return apiService
                .getPopularEndpoint(API_KEY, API_LANG,
                        1)
                .results.asFlow()
    }

    suspend fun loadTopRated(): Flow<Tv> {
        return apiService
                .getTopRatedEndpoint(API_KEY, API_LANG,
                        1)
                .body()?.results!!.asFlow()
    }

    companion object {
        private const val API_KEY = BuildConfig.ApiKey
        private const val API_LANG = "en-US"
        private const val APPEND_TO_RESPONSE = "Seasons,similar,content_ratings,reviews"
        private val TAG = RemoteDataSource::class.java.simpleName
    }
}
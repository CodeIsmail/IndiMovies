package dev.codeismail.data.repository

import dev.codeismail.data.datasource.RemoteDataSource
import dev.codeismail.data.mapper.TvTvShowMapper
import dev.codeismail.domain.IRepository
import dev.codeismail.domain.common.Result
import dev.codeismail.domain.model.TvShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TvShowRepository(private val remoteDataSource: RemoteDataSource) : IRepository {

    @ExperimentalCoroutinesApi
    override suspend fun loadTvShows(): Flow<Result<List<TvShow>>> {
        val popular = TvTvShowMapper(remoteDataSource.loadPopular())
        return flow {
            emit(Result.Success(popular))
        }.catch{e ->
            Result.Error(e)
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private val TAG = TvShowRepository::class.java.simpleName
    }
}

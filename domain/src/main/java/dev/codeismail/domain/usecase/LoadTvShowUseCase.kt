package dev.codeismail.domain.usecase

import dev.codeismail.domain.IRepository
import dev.codeismail.domain.common.Result
import dev.codeismail.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

class LoadTvShowUseCase(private val repository: IRepository){

    suspend fun tvShowObservable(): Flow<Result<List<TvShow>>> {
        return repository.loadTvShows()
    }
}
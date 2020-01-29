package dev.codeismail.domain

import dev.codeismail.domain.common.Result
import dev.codeismail.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun loadTvShows() : Flow<Result<List<TvShow>>>
}
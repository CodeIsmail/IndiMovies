package dev.codeismail.data.mapper

import dev.codeismail.data.model.Tv
import dev.codeismail.data.model.TvEntity
import dev.codeismail.domain.common.Mapper
import dev.codeismail.domain.common.Result

object ResultTvEntityResultListTvMapper: Mapper<Result<TvEntity>, Result<List<Tv>>> {
    override fun invoke(from: Result<TvEntity>): Result<List<Tv>> {
        return when(from){
            is Result.Success -> {
                Result.Success(from.data.results)
            }
            is Result.Error -> {
                Result.Error(from.exception)
            }
            is Result.Loading -> {
                Result.Loading
            }
        }
    }
}
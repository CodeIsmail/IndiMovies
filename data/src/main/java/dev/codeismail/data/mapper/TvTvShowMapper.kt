package dev.codeismail.data.mapper

import dev.codeismail.data.model.Tv
import dev.codeismail.domain.common.Mapper
import dev.codeismail.domain.model.TvShow

object TvTvShowMapper : Mapper<List<Tv>, List<TvShow>> {
    override fun invoke(from: List<Tv>): List<TvShow> {
        return from.map {
            TvShow(
                    backdropPath = it.backdropPath,
                    firstAirDate = it.firstAirDate,
                    genreIds = it.genreIds,
                    id = it.id,
                    name = it.name,
                    originalLanguage = it.originalLanguage,
                    originalName = it.originalName,
                    originCountry = it.originCountry,
                    overview = it.overview,
                    popularity = it.popularity,
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage,
                    voteCount = it.voteCount
            )
        }
    }
}
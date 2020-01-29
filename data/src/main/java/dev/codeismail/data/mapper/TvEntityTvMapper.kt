package dev.codeismail.data.mapper

import dev.codeismail.data.model.Tv
import dev.codeismail.data.model.TvEntity
import dev.codeismail.domain.common.Mapper

object TvEntityTvMapper : Mapper<TvEntity, List<Tv>> {
    override fun invoke(from: TvEntity): List<Tv> = from.results
}
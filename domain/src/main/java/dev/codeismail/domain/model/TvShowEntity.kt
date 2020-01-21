package dev.codeismail.domain.model

data class TvShowEntity(
        val page: Int,
        val results: List<TvShow>,
        val totalPages: Int,
        val totalResults: Int
)
package dev.codeismail.domain

interface IRepository {
    suspend fun loadTvShows()
}
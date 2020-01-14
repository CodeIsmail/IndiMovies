package dev.codeismail.domain.model

import com.google.gson.annotations.SerializedName

data class TvShowEntity(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<TvShow>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)
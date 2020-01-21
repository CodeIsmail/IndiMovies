package dev.codeismail.data.model

import com.google.gson.annotations.SerializedName

data class TvEntity(
        @SerializedName("page")
        val page: Int,
        @SerializedName("results")
        val results: List<Tv>,
        @SerializedName("total_pages")
        val totalPages: Int,
        @SerializedName("total_results")
        val totalResults: Int
)
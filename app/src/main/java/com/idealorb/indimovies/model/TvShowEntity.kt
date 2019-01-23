package com.idealorb.indimovies.model

import com.google.gson.annotations.SerializedName
//import org.parceler.Generated
//
//@Generated("com.robohorse.robopojogenerator")
data class TvShowEntity(

		@field:SerializedName("page")
	val page: Int? = null,

		@field:SerializedName("total_pages")
	val totalPages: Int? = null,

		@field:SerializedName("results")
	val tvShows: List<TvShow?>? = null,

		@field:SerializedName("total_results")
	val totalResults: Int? = null
)
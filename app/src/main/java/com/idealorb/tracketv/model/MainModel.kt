package com.idealorb.tracketv.model

import androidx.paging.PagedList

data class MainModel (val categoryTitle : String,
                      val tvShows : PagedList<TvShow?>?)
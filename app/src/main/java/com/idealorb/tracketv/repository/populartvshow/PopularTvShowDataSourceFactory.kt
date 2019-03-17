package com.idealorb.tracketv.repository.populartvshow

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.idealorb.tracketv.model.TvShow
import com.idealorb.tracketv.services.ITvShowsdbApi

class PopularTvShowDataSourceFactory(private val apiService : ITvShowsdbApi): DataSource.Factory<Int, TvShow?>() {
    val sourceLiveData = MutableLiveData<PopularTvShowDataSource>()
    override fun create(): DataSource<Int, TvShow?> {
        val source = PopularTvShowDataSource(apiService)
        sourceLiveData.postValue(source)
        return source
    }
}
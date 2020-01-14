package com.idealorb.tracketv.repository

import com.idealorb.tracketv.BuildConfig

class TvShowRepository {
//    private val apiService: ITvShowApi = TvShowRemoteDataSource.service
//    private val PAGE_SIZE = 20
//    private val config  = PagedList.Config.Builder()
//            .setEnablePlaceholders(false)
//            .setPageSize(PAGE_SIZE)
//            .setInitialLoadSizeHint(PAGE_SIZE * 3)
//            .setPrefetchDistance(10)
//            .build()
//    fun loadRemotePopularShows(): Observable<PagedList<TvShow?>> {
//        Log.d(TAG, "loadRemotePopularShows(): called")
//        val sourceFactory = PopularTvShowDataSourceFactory(apiService)
//        return RxPagedListBuilder(sourceFactory, config).buildObservable()
//    }
//
//    private suspend fun transform(tvShowEntity: Deferred<TvShowEntity>) : List<TvShow?>{
//        val entity = tvShowEntity.await()
//        return entity.tvShows ?: emptyList()
//    }
//    fun loadRemoteTopRatedShows(): Observable<PagedList<TvShow?>> {
//        Log.d(TAG, "loadRemoteTopRatedShows(): called")
//        val sourceFactory = TopRatedTvShowDataSourceFactory(apiService)
//        return RxPagedListBuilder(sourceFactory, config).buildObservable()
//    }
//    fun loadRemoteTrendingShows(): Observable<PagedList<TvShow?>> {
//        Log.d(TAG, "loadRemoteTrendingShows(): called")
//        val sourceFactory = TrendingTvShowDataSourceFactory(apiService)
//        return RxPagedListBuilder(sourceFactory, config).buildObservable()
//    }
//
//    fun loadHeaders(): List<String>{
//        Log.d(TAG, "loadHeaders(): called")
//        return arrayListOf("Trending", "Popular", "Top Rated")
//    }
//
//    fun loadMainViewData(tvShows : List<PagedList<TvShow?>>): List<TvShowCategoryEntity>{
//        Log.d(TAG, "loadMainViewData(): called")
//        val mainModels = ArrayList<TvShowCategoryEntity>()
//        val size = loadHeaders().size
//        if(tvShows.isNotEmpty()){
//            for (i in 0 until size){
//                mainModels.add(TvShowCategoryEntity(loadHeaders()[i], tvShows[i]))
//            }
//        }
//        return mainModels
//    }

    //    suspend fun loadTvShowDetail(id: Int): TvShowDetailEntity {
//        return apiService.getTvShow(id, API_KEY, API_LANG, APPEND_TO_RESPONSE).await()
//    }
    companion object {
        private const val API_KEY = BuildConfig.ApiKey
        private const val API_LANG = "en-US"
        private const val APPEND_TO_RESPONSE = "Seasons,similar,content_ratings,reviews"
        private val TAG = TvShowRepository::class.java.simpleName
    }

}
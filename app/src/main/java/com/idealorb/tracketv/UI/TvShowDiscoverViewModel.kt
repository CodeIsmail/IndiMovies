package com.idealorb.tracketv.UI

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idealorb.tracketv.model.MainModel
import com.idealorb.tracketv.repository.TvShowRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function3
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class TvShowDiscoverViewModel(private val repo: TvShowRepository) : ViewModel(), CoroutineScope {

    private var tvShowsData: MutableLiveData<List<MainModel>> = MutableLiveData()
    private val liveDataMerger: MediatorLiveData<MainModel> = MediatorLiveData()
    val data = ArrayList<MainModel>()
    //    private val mainData: MutableLiveData<List<MainModel>> = MutableLiveData()
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val pagedListPopularShows = repo.loadRemotePopularShows()
            .map { tvShow -> MainModel("Popular", tvShow) }
    private val pagedListTopRatedShows = repo.loadRemoteTopRatedShows()
            .map { tvShow -> MainModel("Top Rated", tvShow) }
    private val pagedListTrendingShows = repo.loadRemoteTrendingShows()
            .map { tvShow -> MainModel("Trending", tvShow) }

    val mergedDataSource = Observable.zip(pagedListTrendingShows, pagedListPopularShows,
            pagedListTopRatedShows, Function3<MainModel, MainModel, MainModel, List<MainModel>>{
        t1, t2, t3 -> listOf(t1, t2, t3)
    })

    val mainView = LiveDataReactiveStreams.fromPublisher(mergedDataSource.toFlowable(BackpressureStrategy.LATEST))


    companion object {
        private val TAG = TvShowDiscoverViewModel::class.java.simpleName
    }

}

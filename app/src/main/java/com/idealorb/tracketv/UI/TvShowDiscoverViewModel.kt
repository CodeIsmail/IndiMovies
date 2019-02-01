package com.idealorb.tracketv.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.idealorb.tracketv.model.MainModel
import com.idealorb.tracketv.model.TvShow
import com.idealorb.tracketv.model.TvShowEntity
import com.idealorb.tracketv.repository.TvShowRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TvShowDiscoverViewModel(private val repo: TvShowRepository) : ViewModel() {

    private var tvShowsData: MutableLiveData<List<List<TvShow?>>> = MutableLiveData()
//    private val mainData: MutableLiveData<List<MainModel>> = MutableLiveData()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private fun loadShows(): LiveData<List<List<TvShow?>>>{
        Log.d(TAG, "loadShows():called ")
        scope.launch {
            try {
                val deferredPopularShows = repo.loadRemotePopularShows()
                val deferredTopRatedShows = repo.loadRemoteTopRatedShows()
                val deferredTrendingShows = repo.loadRemoteTrendingShows()
                tvShowsData.value = merger(deferredPopularShows.await(), deferredTopRatedShows.await(),
                        deferredTrendingShows.await())
                Log.d(TAG, "show tvShowsData: ${tvShowsData.value?.size} ")
            }catch (ex: Exception){
                Log.d(TAG, ex.message)
                ex.printStackTrace()
            }
        }
        return tvShowsData
    }
    private fun merger(popularShow: TvShowEntity, topRatedShow: TvShowEntity,
                       trendingShow: TvShowEntity): List<List<TvShow?>>{
        return arrayListOf(trendingShow.tvShows!!, popularShow.tvShows!!,
                topRatedShow.tvShows!!)
    }

    private fun mainModelMapper(tvShows: List<List<TvShow?>>): List<MainModel>{
        Log.d(TAG, "tv show data size: ${tvShows.size} ")
        return repo.loadMainViewData(tvShows)
    }
    fun loadMainView(): LiveData<List<MainModel>>{
        Log.d(TAG, "loadMainView():called ")
        return Transformations
                .map(loadShows()) {
                    tvShows -> mainModelMapper(tvShows)}
    }

    companion object {
        private val TAG = TvShowDiscoverViewModel::class.java.simpleName
    }
}

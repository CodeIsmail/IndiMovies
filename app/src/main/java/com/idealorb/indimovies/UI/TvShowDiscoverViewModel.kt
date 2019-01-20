package com.idealorb.indimovies.UI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.idealorb.indimovies.Repository.MovieRepository
import com.idealorb.indimovies.model.MainModel
import com.idealorb.indimovies.model.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TvShowDiscoverViewModel(private val repo: MovieRepository) : ViewModel() {

    private var tvShowsData: MutableLiveData<List<TvShow?>> = MutableLiveData()
//    private val mainData: MutableLiveData<List<MainModel>> = MutableLiveData()
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    private fun loadShows(): LiveData<List<TvShow?>>{
        Log.d(TAG, "loadShows():called ")
        scope.launch {
            try {
                val deferredTvShowEntity = repo.loadRemoteShows()
                tvShowsData.value = deferredTvShowEntity.await().tvShows
                Log.d(TAG, "show tvShowsData: ${tvShowsData.value?.size} ")
            }catch (ex: Exception){
                Log.d(TAG, ex.message)
                ex.printStackTrace()
            }
        }
        return tvShowsData
    }

    private fun mainModelMapper(tvShows: List<TvShow?>): List<MainModel>{
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

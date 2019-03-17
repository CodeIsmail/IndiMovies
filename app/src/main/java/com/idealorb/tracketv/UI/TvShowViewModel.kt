package com.idealorb.tracketv.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idealorb.tracketv.model.TvShowDetailEntity
import com.idealorb.tracketv.repository.TvShowRepository
import kotlinx.coroutines.*

class TvShowViewModel(private val repo: TvShowRepository) : ViewModel(){
    private val tvDetailData = MutableLiveData<TvShowDetailEntity>()
    private val viewModelJob = Job()
    private val scope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getTvShowDetails(tvShowId: Int): LiveData<TvShowDetailEntity>{

        scope.launch {
            val entity = repo.loadTvShowDetail(tvShowId)
            tvDetailData.value = entity
        }
        return tvDetailData
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }
}

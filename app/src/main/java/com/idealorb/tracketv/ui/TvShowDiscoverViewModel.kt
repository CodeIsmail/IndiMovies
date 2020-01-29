package com.idealorb.tracketv.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.codeismail.domain.common.Result
import dev.codeismail.domain.model.TvShow
import dev.codeismail.domain.usecase.LoadTvShowUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TvShowDiscoverViewModel(private val loadTvShowUseCase: LoadTvShowUseCase) : ViewModel() {

    val tvShowList = MutableLiveData<Result<List<TvShow>>>()
   fun getData(): LiveData<Result<List<TvShow>>>{
       viewModelScope.launch {
           loadTvShowUseCase.tvShowObservable().collect {
               tvShowList.value = it
           }
       }
       return tvShowList
   }
    companion object {
        private val TAG = TvShowDiscoverViewModel::class.java.simpleName
    }

}

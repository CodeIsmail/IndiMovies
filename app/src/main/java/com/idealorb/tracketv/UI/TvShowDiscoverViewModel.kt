package com.idealorb.tracketv.UI

import androidx.lifecycle.ViewModel
import com.idealorb.tracketv.repository.TvShowRepository

class TvShowDiscoverViewModel(private val repo: TvShowRepository) : ViewModel() {

    companion object {
        private val TAG = TvShowDiscoverViewModel::class.java.simpleName
    }

}

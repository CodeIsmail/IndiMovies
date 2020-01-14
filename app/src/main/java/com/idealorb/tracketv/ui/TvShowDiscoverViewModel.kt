package com.idealorb.tracketv.ui

import androidx.lifecycle.ViewModel
import dev.codeismail.data.repository.TvShowRepository

class TvShowDiscoverViewModel(private val repo: TvShowRepository) : ViewModel() {

    companion object {
        private val TAG = TvShowDiscoverViewModel::class.java.simpleName
    }

}

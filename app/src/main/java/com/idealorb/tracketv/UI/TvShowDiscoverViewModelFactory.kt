package com.idealorb.tracketv.UI

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idealorb.tracketv.repository.TvShowRepository

class TvShowDiscoverViewModelFactory private constructor(private val repository: TvShowRepository) : ViewModelProvider.Factory {

    companion object {
        private val TAG = TvShowDiscoverViewModelFactory::class.java.simpleName
        fun createFactory(): TvShowDiscoverViewModelFactory {
            Log.d(TAG, "createFactory(): called")
            return TvShowDiscoverViewModelFactory(TvShowRepository())
        }
    }
    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(TvShowRepository::class.java).newInstance(repository)
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}


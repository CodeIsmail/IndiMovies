package com.idealorb.indimovies.UI

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.idealorb.indimovies.Repository.MovieRepository

class TvShowDiscoverViewModelFactory private constructor(private val repository: MovieRepository) : ViewModelProvider.Factory {

    companion object {
        private val TAG = TvShowDiscoverViewModelFactory::class.java.simpleName
        fun createFactory(): TvShowDiscoverViewModelFactory {
//        val context = activity.applicationContext
//                ?: throw IllegalStateException("Not yet attached to Application")
            Log.d(TAG, "createFactory(): called")
            return TvShowDiscoverViewModelFactory(MovieRepository())
        }
    }
    @NonNull
    override fun <T : ViewModel> create(@NonNull modelClass: Class<T>): T {
        try {
            return modelClass.getConstructor(MovieRepository::class.java).newInstance(repository)
        } catch (e: InstantiationException) {
            throw RuntimeException("Cannot create an instance of $modelClass", e)
        }
    }
}


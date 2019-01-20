package com.idealorb.indimovies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idealorb.indimovies.R
import com.idealorb.indimovies.model.TvShow

class TvShowAdapter(private val moviesList: List<TvShow?>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnClickMovieListener {
        fun onClickMovie(tvShow: TvShow)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.movies_list_item, parent, false)
        return TvShowViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieObject = moviesList?.get(position)
        Log.d(TAG, "Tv Show Name: ${movieObject?.name}")
        (holder as TvShowViewHolder).bindView(movieObject)
    }

    fun setTvShowData(tvShows: List<TvShow>){
//        moviesList?.addAll(tvShows)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "Tv Show List size: ${moviesList!!.size}")
        return moviesList.size
    }



    companion object {
        private val TAG = TvShowAdapter::class.java.simpleName
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }

}

package com.idealorb.indimovies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.idealorb.indimovies.model.TvShow

class TvShowDiffCallback(private val newTvShows : List<TvShow?>?, private val oldTvShows : List<TvShow?>?): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTvShow = oldTvShows!![oldItemPosition]
        val newTvShow = newTvShows!![newItemPosition]
        return oldTvShow?.id == newTvShow?.id
    }

    override fun getOldListSize(): Int = oldTvShows!!.size

    override fun getNewListSize(): Int = newTvShows!!.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldTvShow = oldTvShows!![oldItemPosition]
        val newTvShow = newTvShows!![newItemPosition]
        return oldTvShow == newTvShow
    }
}
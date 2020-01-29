package com.idealorb.tracketv.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dev.codeismail.domain.model.TvShow


class TvShowAdapter :
        ListAdapter<TvShow, TvShowViewHolder>(TvShowDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        holder.bindView(tvShow)
    }
    companion object {
        private val TAG = TvShowAdapter::class.java.simpleName
        private val TvShowDiffCallback = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                    oldItem == newItem
        }
    }

}

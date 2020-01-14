package com.idealorb.tracketv.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idealorb.tracketv.R
import dev.codeismail.domain.model.TvShowCategoryEntity

class TvShowCategoryAdapter(private var tvShowCategoryEntities: List<TvShowCategoryEntity>) : RecyclerView.Adapter<TvShowCategoryViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowCategoryViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_tvshow_category_list_item, parent, false)
        return TvShowCategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tvShowCategoryEntities.size
    }

    override fun onBindViewHolder(holder: TvShowCategoryViewHolder, position: Int) {
        val mainModel = tvShowCategoryEntities[position]
        holder.bindView(mainModel, viewPool)
    }

    fun updateMainModelData(newTvShowCategoryEntities: List<TvShowCategoryEntity>) {
        DiffUtil.calculateDiff(TvShowCategoryDiffCallback(newTvShowCategoryEntities, tvShowCategoryEntities), false).dispatchUpdatesTo(this)
        tvShowCategoryEntities = newTvShowCategoryEntities
        Log.d("MainModelAdapter", "updateMainModelData called")
    }
}
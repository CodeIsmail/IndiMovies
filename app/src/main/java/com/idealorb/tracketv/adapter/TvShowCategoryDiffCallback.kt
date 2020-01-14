package com.idealorb.tracketv.adapter

import androidx.recyclerview.widget.DiffUtil
import dev.codeismail.domain.model.TvShowCategoryEntity

class TvShowCategoryDiffCallback(private val newTvShowCategoryEntities: List<TvShowCategoryEntity>, private val oldTvShowCategoryEntities: List<TvShowCategoryEntity>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMainModel = oldTvShowCategoryEntities[oldItemPosition]
        val newMainModel = newTvShowCategoryEntities[newItemPosition]
        return oldMainModel.categoryTitle == newMainModel.categoryTitle
    }

    override fun getOldListSize(): Int = oldTvShowCategoryEntities.size

    override fun getNewListSize(): Int = newTvShowCategoryEntities.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMainModel = oldTvShowCategoryEntities[oldItemPosition]
        val newMainModel = newTvShowCategoryEntities[newItemPosition]
        return oldMainModel == newMainModel
    }
}
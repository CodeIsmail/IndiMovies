package com.idealorb.tracketv.adapter

import androidx.recyclerview.widget.DiffUtil
import com.idealorb.tracketv.model.MainModel

class MainViewDiffCallback(private val newMainModels : List<MainModel>, private val oldMainModels : List<MainModel>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMainModel = oldMainModels[oldItemPosition]
        val newMainModel = newMainModels[newItemPosition]
        return oldMainModel.categoryTitle == newMainModel.categoryTitle
    }

    override fun getOldListSize(): Int = oldMainModels.size

    override fun getNewListSize(): Int = newMainModels.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldMainModel = oldMainModels[oldItemPosition]
        val newMainModel = newMainModels[newItemPosition]
        return oldMainModel == newMainModel
    }
}
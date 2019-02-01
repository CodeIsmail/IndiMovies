package com.idealorb.indimovies.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.idealorb.indimovies.R
import com.idealorb.indimovies.model.MainModel

class MainViewAdapter(private var mainModels: List<MainModel>) : RecyclerView.Adapter<MainViewHolder>() {


    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context)
                .inflate(R.layout.main_list_item, parent, false)
        return MainViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mainModels.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val mainModel = mainModels[position]
        holder.bindView(mainModel, viewPool)
    }

    fun updateMainModelData(newMainModels: List<MainModel>){
        DiffUtil.calculateDiff(MainViewDiffCallback(newMainModels, mainModels), false).dispatchUpdatesTo(this)
        mainModels = newMainModels
        Log.d("MainModelAdapter", "updateMainModelData called")
    }
}
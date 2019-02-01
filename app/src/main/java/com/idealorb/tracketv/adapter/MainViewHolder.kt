package com.idealorb.tracketv.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.idealorb.tracketv.model.MainModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.main_list_item.*

class MainViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer{
    fun bindView(mainModel: MainModel, viewPool: RecyclerView.RecycledViewPool) {
        val context = child_recycler_view.context
        show_category.text = mainModel.categoryTitle
        val tvShowsLayout = LinearLayoutManager(context, HORIZONTAL, false)
        tvShowsLayout.initialPrefetchItemCount = 3
        if (child_recycler_view.adapter == null){
            child_recycler_view.apply {
                layoutManager = tvShowsLayout
                adapter = TvShowAdapter(mainModel.tvShows)
                setRecycledViewPool(viewPool)
            }
        }else {
            (child_recycler_view.adapter as TvShowAdapter).updateTvShowData(mainModel.tvShows)
        }
    }


}

package com.idealorb.tracketv.adapter

import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.idealorb.tracketv.UI.TvShowDiscoverFragmentDirections
import com.idealorb.tracketv.model.MainModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.main_list_item.*

class MainViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
    fun bindView(mainModel: MainModel, viewPool: RecyclerView.RecycledViewPool) {
        val context = child_recycler_view.context
        show_category.text = mainModel.categoryTitle
        val tvShowsLayout = LinearLayoutManager(context, HORIZONTAL, false)
        tvShowsLayout.initialPrefetchItemCount = 4
        val tvShowAdapter = TvShowAdapter{ view, tvShow ->
            Log.d("MainViewHolder", "tv show name : ${tvShow.name}")
            val action = TvShowDiscoverFragmentDirections
                    .actionTvShowDiscoverFragmentToTvShowFragment(tvShow.id!!)
            view.findNavController().navigate(action)
        }

        tvShowAdapter.submitList(mainModel.tvShows)
        child_recycler_view.apply {
            layoutManager = tvShowsLayout
            adapter = tvShowAdapter
            setRecycledViewPool(viewPool)
        }
    }


}

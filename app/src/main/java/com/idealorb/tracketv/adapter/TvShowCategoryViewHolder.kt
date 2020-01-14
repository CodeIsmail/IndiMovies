package com.idealorb.tracketv.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import dev.codeismail.domain.model.TvShowCategoryEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_tvshow_category_list_item.*

class TvShowCategoryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
    fun bindView(tvShowCategoryEntity: TvShowCategoryEntity, viewPool: RecyclerView.RecycledViewPool) {
        val context = child_recycler_view.context
        tvshow_category.text = tvShowCategoryEntity.categoryTitle
        val tvShowsLayout = LinearLayoutManager(context, HORIZONTAL, false)
        tvShowsLayout.initialPrefetchItemCount = 4
        val tvShowAdapter = TvShowAdapter { view, tvShow ->
//            val action = TvShowDiscoverFragmentDirections
//                    .actionTvShowDiscoverFragmentToTvShowFragment(tvShow.id)
//            view.findNavController().navigate(action)
        }

        tvShowAdapter.submitList(tvShowCategoryEntity.tvShows)
        child_recycler_view.apply {
            layoutManager = tvShowsLayout
            adapter = tvShowAdapter
            setRecycledViewPool(viewPool)
        }
    }


}

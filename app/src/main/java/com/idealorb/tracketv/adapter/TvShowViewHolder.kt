package com.idealorb.tracketv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.idealorb.tracketv.MOVIE_POSTER_BASEURL
import com.idealorb.tracketv.R
import dev.codeismail.domain.model.TvShow
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_tvshow_list_item.*

class TvShowViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindView(tvShow : TvShow) {
        if (tvShow.posterPath != null){
            tvshow_thumbnail_iv.load(getMovieThumbnailUrl(tvShow)){
                placeholder(R.drawable.ic_placeholder)
            }
        }else{
            tvshow_thumbnail_iv.load(R.drawable.ic_placeholder){
                placeholder(R.drawable.ic_placeholder)
            }
        }
    }

    private fun getMovieThumbnailUrl(tvShow: TvShow): String {
        return MOVIE_POSTER_BASEURL + tvShow.posterPath
    }

    companion object {
        fun create(parent: ViewGroup): TvShowViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_tvshow_list_item, parent, false)
            return TvShowViewHolder(view)
        }
    }
}

package com.idealorb.tracketv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.idealorb.tracketv.R
import dev.codeismail.domain.model.TvShow
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_tvshow_list_item.*

class TvShowViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindView(tvShow : TvShow?, listener:(view: View, tvShow: TvShow)->Unit) {
        tvshow_thumbnail_iv.load(getMovieThumbnailUrl(tvShow!!))
        containerView.setOnClickListener {
            listener(it, tvShow)
        }
    }

    private fun getMovieThumbnailUrl(tvShow: TvShow): String {
        val MOVIE_POSTER_BASEURL = "http://image.tmdb.org/t/p/w185/"
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

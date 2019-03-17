package com.idealorb.tracketv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.idealorb.tracketv.R
import com.idealorb.tracketv.model.TvShow
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.tv_list_item.*

class TvShowViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {


    fun bindView(tvShow : TvShow?, listener:(view: View, tvShow: TvShow)->Unit) {
        Picasso.get()
                .load(getMovieThumbnailUrl(tvShow!!))
                .placeholder(R.color.placeHolder)
                .into(tvshow_thumbnail_iv)
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
                    .inflate(R.layout.tv_list_item, parent, false)
            return TvShowViewHolder(view)
        }
    }
}

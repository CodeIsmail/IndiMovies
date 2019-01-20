package com.idealorb.indimovies.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.idealorb.indimovies.R
import com.idealorb.indimovies.model.TvShow
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.movies_list_item.*

class TvShowViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bindView(tvShow : TvShow?) {
        textView.text = tvShow?.name
        Picasso.get()
                .load(getMovieThumbnailUrl(tvShow!!))
                .placeholder(R.color.placeHolder)
                .into(movie_thumbnail_iv)
    }

    private fun getMovieThumbnailUrl(tvShow: TvShow): String {
        val MOVIE_POSTER_BASEURL = "http://image.tmdb.org/t/p/w342/"
        return MOVIE_POSTER_BASEURL + tvShow.posterPath
    }
}
package com.idealorb.indimovies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idealorb.indimovies.R;
import com.idealorb.indimovies.model.MoviesModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.movie_thumbnail_iv)
    ImageView movieThumbnailIV;
    @BindView(R.id.rate_count_tv)
    TextView ratingCountTV;
    private final List<MoviesModel> moviesList;
    private MoviesAdapter.OnClickMovieListener movieListener;

    ItemViewHolder(View itemView, List<MoviesModel> moviesList,
                   MoviesAdapter.OnClickMovieListener movieListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.moviesList = moviesList;
        this.movieListener = movieListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int adapterPosition = getAdapterPosition() - 1;
        MoviesModel moviesModel = moviesList.get(adapterPosition);
        movieListener.onClickMovie(moviesModel);
    }
}

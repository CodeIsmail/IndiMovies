package com.idealorb.indimovies.adapter;

import android.support.v7.util.DiffUtil;

import com.idealorb.indimovies.model.MoviesModel;

import java.util.List;

public class MoviesDiffCallback extends DiffUtil.Callback {

    private final List<MoviesModel> oldMoviesList;
    private final List<MoviesModel> newMoviesList;

    public MoviesDiffCallback(List<MoviesModel> oldMoviesList, List<MoviesModel> newMoviesList) {
        this.oldMoviesList = oldMoviesList;
        this.newMoviesList = newMoviesList;
    }

    @Override
    public int getOldListSize() {
        return oldMoviesList == null ? 0 : oldMoviesList.size();
    }

    @Override
    public int getNewListSize() {
        return newMoviesList == null ? 0 : newMoviesList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        int oldMovieId = oldMoviesList.get(oldItemPosition).getId();
        int newMovieId = newMoviesList.get(newItemPosition).getId();

        return oldMovieId == newMovieId;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final MoviesModel oldMovie = oldMoviesList.get(oldItemPosition);
        final MoviesModel newMovie = newMoviesList.get(newItemPosition);

        return oldMovie.getTitle().equals(newMovie.getTitle());
    }
}

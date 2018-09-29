package com.idealorb.indimovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.idealorb.indimovies.R;
import com.idealorb.indimovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    private final List<Movie> moviesList;
    private int moviesSortType;
    private OnClickMovieListener onClickMovieListener;

    public interface OnClickMovieListener{
        void onClickMovie(Movie movie);

    }

    public MoviesAdapter(OnClickMovieListener onClickMovieListener, List<Movie> moviesList) {
        this.onClickMovieListener = onClickMovieListener;
        this.moviesList = moviesList;
        moviesSortType = R.string.empty_header_label;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        if (viewType == TYPE_HEADER){
            View headerView = LayoutInflater.from(context)
                    .inflate(R.layout.movies_list_header, parent, false);
            return new HeaderViewHolder(headerView);
        }else if (viewType == TYPE_ITEM){
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.movies_list_item, parent, false);
            return new ItemViewHolder(itemView, moviesList, onClickMovieListener);
        }
        throw new RuntimeException("No match for "+ viewType + ".");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HeaderViewHolder){
            ((HeaderViewHolder) holder).headerTextView.setText(moviesSortType);
        }else{
            Movie movieObject = moviesList.get(position - 1);
            ((ItemViewHolder) holder).ratingCountTV.setText(String.format(Locale.ENGLISH,
                    "%.1f", movieObject.getVoteAverage()));
            Picasso.get()
                    .load(getMovieThumbnailUrl(movieObject))
                    .placeholder(R.color.placeHolder)
                    .into(((ItemViewHolder)holder).movieThumbnailIV);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return (moviesList == null) ? 0 : moviesList.size() + 1;
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList.clear();
        this.moviesList.addAll(moviesList);
        notifyDataSetChanged();

    }

    public void setHeader(int sortType) {
        if (sortType == 0)
            moviesSortType = R.string.most_popular_label;
        else
            moviesSortType = R.string.highest_rated_label;
    }

    private String getMovieThumbnailUrl(Movie movie) {
        String MOVIE_POSTER_BASEURL = "http://image.tmdb.org/t/p/w342/";
        return MOVIE_POSTER_BASEURL + movie.getPosterPath();
    }

}

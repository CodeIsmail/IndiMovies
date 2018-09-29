package com.idealorb.indimovies.network;

import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.MovieJsonUtil;
import com.idealorb.indimovies.model.ReviewJsonUtil;
import com.idealorb.indimovies.model.TrailerJsonUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMoviesdbApi {

    @GET("movie/popular")
    Call<MovieJsonUtil> getPopularMovies(@Query("api_key") String api_key,
                                         @Query("language") String lang,
                                         @Query("page") int pageCount);

    @GET("movie/top_rated")
    Call<MovieJsonUtil> getTopRatedMovies(@Query("api_key") String api_key,
                                          @Query("language") String lang,
                                          @Query("page") int pageCount);

    @GET("movie/{movie_id}")
    Call<MovieDetailJsonUtil> getMovieDetail(@Path("movie_id") int movieId,
                                             @Query("api_key") String api_key,
                                             @Query("language") String lang,
                                             @Query("append_to_response") String releaseDates);

    @GET("movie/{movie_id}/videos")
    Call<TrailerJsonUtil> getMovieVideos(@Path("movie_id") int movieId,
                                         @Query("api_key") String api_key,
                                         @Query("language") String lang);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewJsonUtil> getMovieReviews(@Path("movie_id") int movieId,
                                        @Query("api_key") String api_key,
                                        @Query("language") String lang,
                                         @Query("page") int pageCount);
}

package com.idealorb.indimovies.network;

import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.MovieJsonUtil;
import com.idealorb.indimovies.model.ReviewJsonUtil;
import com.idealorb.indimovies.model.TrailerJsonUtil;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMoviesdbApi {

    @GET("movie/popular")
    Single<MovieJsonUtil> getPopularMovies(@Query("api_key") String api_key,
                                           @Query("language") String lang,
                                           @Query("page") int pageCount);

    @GET("movie/top_rated")
    Single<MovieJsonUtil> getTopRatedMovies(@Query("api_key") String api_key,
                                          @Query("language") String lang,
                                          @Query("page") int pageCount);

    @GET("movie/{movie_id}")
    Single<MovieDetailJsonUtil> getMovieDetail(@Path("movie_id") int movieId,
                                             @Query("api_key") String api_key,
                                             @Query("language") String lang,
                                             @Query("append_to_response") String releaseDates);

    @GET("movie/{movie_id}/videos")
    Single<TrailerJsonUtil> getMovieVideos(@Path("movie_id") int movieId,
                                         @Query("api_key") String api_key,
                                         @Query("language") String lang);

    @GET("movie/{movie_id}/reviews")
    Single<ReviewJsonUtil> getMovieReviews(@Path("movie_id") int movieId,
                                        @Query("api_key") String api_key,
                                        @Query("language") String lang,
                                         @Query("page") int pageCount);
}

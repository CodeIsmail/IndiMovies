package com.idealorb.indimovies.network;

import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.MovieJsonUtil;
import com.idealorb.indimovies.model.ReviewJsonUtil;
import com.idealorb.indimovies.model.TrailerJsonUtil;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMoviesdbApi {

    @GET("movie/popular")
    Observable<MovieJsonUtil> getPopularMovies(@Query("api_key") String api_key,
                                               @Query("language") String lang,
                                               @Query("page") int pageCount);

    @GET("movie/top_rated")
    Observable<MovieJsonUtil> getTopRatedMovies(@Query("api_key") String api_key,
                                          @Query("language") String lang,
                                          @Query("page") int pageCount);

    @GET("movie/{movie_id}")
    Observable<MovieDetailJsonUtil> getMovieDetail(@Path("movie_id") int movieId,
                                             @Query("api_key") String api_key,
                                             @Query("language") String lang,
                                             @Query("append_to_response") String releaseDates);

    @GET("movie/{movie_id}/videos")
    Observable<TrailerJsonUtil> getMovieVideos(@Path("movie_id") int movieId,
                                         @Query("api_key") String api_key,
                                         @Query("language") String lang);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewJsonUtil> getMovieReviews(@Path("movie_id") int movieId,
                                        @Query("api_key") String api_key,
                                        @Query("language") String lang,
                                         @Query("page") int pageCount);
}

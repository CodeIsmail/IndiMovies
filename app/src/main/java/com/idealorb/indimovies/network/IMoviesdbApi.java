package com.idealorb.indimovies.network;

import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.MovieTrailersJSONUtil;
import com.idealorb.indimovies.model.MoviesJsonUtil;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IMoviesdbApi {

    @GET("movie/popular")
    Call<MoviesJsonUtil> getPopularMovies(@Query("api_key") String api_key,
                                          @Query("language") String lang,
                                          @Query("page") int pageCount
    );

    @GET("movie/top_rated")
    Call<MoviesJsonUtil> getTopRatedMovies(@Query("api_key") String api_key,
                                           @Query("language") String lang,
                                           @Query("page") int pageCount);

    @GET("movie/{movie_id}")
    Call<MovieDetailJsonUtil> getMovieDetail(@Path("movie_id") int movieId,
                                             @Query("api_key") String api_key,
                                             @Query("language") String lang,
                                             @Query("append_to_response") String releaseDates);

    @GET("/movie/{movie_id}/videos")
    Call<MovieTrailersJSONUtil> getMovieVideos(@Path("movie_id") int movieId,
                                               @Query("api_key") String api_key,
                                               @Query("language") String lang);
}

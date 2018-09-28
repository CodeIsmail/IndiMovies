package com.idealorb.indimovies.network;

import com.idealorb.indimovies.model.MoviesModel;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesRemoteDataSource {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private IMoviesdbApi moviesdbApi;
    private List<MoviesModel> moviesModelList;
    private static Retrofit retrofit;

    public MoviesRemoteDataSource() {

    }

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

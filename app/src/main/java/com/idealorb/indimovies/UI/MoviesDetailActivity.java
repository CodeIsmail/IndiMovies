package com.idealorb.indimovies.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.idealorb.indimovies.BuildConfig;
import com.idealorb.indimovies.R;
import com.idealorb.indimovies.model.Genre;
import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.MovieReleaseDatesJSONUtil;
import com.idealorb.indimovies.model.MoviesModel;
import com.idealorb.indimovies.model.ReleaseDate;
import com.idealorb.indimovies.network.IMoviesdbApi;
import com.idealorb.indimovies.network.MoviesRemoteDataSource;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesDetailActivity extends AppCompatActivity {

    private static final String TAG = MoviesDetailActivity.class.getSimpleName();
    private MoviesModel movie;

    @BindView(R.id.movie_title_tv)
    TextView movieTitleTV;
    @BindView(R.id.release_date_tv)
    TextView movieReleaseDateTV;
    @BindView(R.id.movie_runtime_tv)
    TextView movieRuntimeTV;
    @BindView(R.id.genres_tv)
    TextView movieGenresTV;
    @BindView(R.id.certification_tv)
    TextView movieCertTV;
    @BindView(R.id.movie_header_iv)
    ImageView movieHeaderIV;
    @BindView(R.id.rating_count_label_tv)
    TextView movieRatingTV;
    @BindView(R.id.movie_overview_tv)
    TextView movieOverviewTV;
    @BindView(R.id.overview_header_tv)
    TextView overviewHeaderTV;
    //    @BindView(R.id.rating_header_tv)
//    TextView ratingHeaderTV;
    @BindView(R.id.movie_runtime_iv)
    ImageView runtimeIV;
    @BindView(R.id.release_date_iv)
    ImageView releaseDateIV;
    //    @BindView(R.id.press_play_iv)
//    ImageView pressplayIV;
    @BindView(R.id.child_toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Intent fromMainActivityIntent = getIntent();

        if (fromMainActivityIntent != null) {
            Bundle movieBundle = fromMainActivityIntent.getBundleExtra(Intent.EXTRA_TEXT);

            movie = Parcels.unwrap(movieBundle.getParcelable("Movie"));
            movieDetail(Objects.requireNonNull(movie));
        }
        movieTitleTV.setText(movie.getTitle());
        movieRatingTV.setText(String.format(Locale.getDefault(),
                "%.1f", movie.getVoteAverage()));

        movieReleaseDateTV.setText(extractYear(movie.getReleaseDate()));
        movieOverviewTV.setText(movie.getOverview());
        overviewHeaderTV.setText(R.string.movie_overview);
        //pressplayIV.setImageResource(R.drawable.ic_press_play);
        runtimeIV.setImageResource(R.drawable.ic_timer);
        releaseDateIV.setImageResource(R.drawable.ic_date);
        Picasso.get()
                .load(getMovieThumbnailUrl(movie.getBackdropPath()))
                .placeholder(R.color.placeHolder)
                .into(movieHeaderIV);
    }

    private void movieDetail(MoviesModel moviesModel) {
        int movieId = moviesModel.getId();
        String apiKey = BuildConfig.ApiKey;
        IMoviesdbApi moviesdbApi = MoviesRemoteDataSource.getRetrofitInstance()
                .create(IMoviesdbApi.class);
        Call<MovieDetailJsonUtil> retrofitcall = moviesdbApi
                .getMovieDetail(movieId, apiKey, "en-US", "release_dates");
        retrofitcall.enqueue(new Callback<MovieDetailJsonUtil>() {
            @Override
            public void onResponse(@NonNull Call<MovieDetailJsonUtil> call, @NonNull Response<MovieDetailJsonUtil> response) {
                if (response.body() != null) {
                    bindRemoteDataToViews(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieDetailJsonUtil> call, @NonNull Throwable t) {

            }
        });
    }

    private void bindRemoteDataToViews(MovieDetailJsonUtil movieDetailUtil) {

        movieRuntimeTV.setText(
                formatMovieRuntime(movieDetailUtil.getRuntime()));
        movieGenresTV.setText(formatMovieGenres(movieDetailUtil.getGenres()));
        if (movieDetailUtil.getReleaseDates() != null) {
            movieCertTV.setText(getMovieCert(movieDetailUtil));
        } else {
            Log.d("MoviesDetailActivity", "Release Dates is null");
        }

    }


    private String getMovieCert(MovieDetailJsonUtil movieDetailJsonUtil) {

        List<MovieReleaseDatesJSONUtil> countryCerts = movieDetailJsonUtil.getReleaseDates()
                .getMovieReleaseDatesJSONUtils();
        String movieCert = "";
        ReleaseDate releaseDate;
        for (int i = 0; i < countryCerts.size(); i++) {
            String locale = countryCerts.get(i).getIso31661();
            releaseDate = countryCerts.get(i)
                    .getReleaseDates().get(0);
            if (locale.equals(Locale.getDefault().getCountry())) {
                movieCert = aboutMovieCert(releaseDate.getCertification());
                break;
            } else {
                movieCert = aboutMovieCert(releaseDate.getCertification());
                Log.d(TAG, movieCert);

            }
        }
        return movieCert;
    }

    private String aboutMovieCert(String movieCert) {
        return TextUtils.isEmpty(movieCert) ? "-" : movieCert;
    }
    private String formatMovieGenres(List<Genre> genres) {
        int size = genres.size();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {

            if ((i + 1) == size) {
                if (genres.get(i).getName().equals("Science Fiction"))
                    builder.append("Sci-Fi");
                else
                    builder.append(genres.get(i).getName());
                break;
            } else {
                if (genres.get(i).getName().equals("Science Fiction"))
                    builder.append("Sci-Fi").append(" | ");
                else
                    builder.append(genres.get(i).getName()).append(" | ");
            }
        }
        return builder.toString();
    }

    private String formatMovieRuntime(Integer runtime) {
        String runtimeString = "-";
        if (runtime != null) {
            int hours = runtime / 60;
            int mins = runtime % 60;
            runtimeString = String.format(
                    Locale.getDefault(), "%d%s %d%s", hours, "h", mins, "min");
        }
        return runtimeString;
    }

    private String extractYear(String releaseDate) {
        String[] split = releaseDate.split("-");
        return split[0];
    }

    private String getMovieThumbnailUrl(String headerImagePath) {
        String MOVIE_POSTER_BASEURL = "http://image.tmdb.org/t/p/original/";
        return MOVIE_POSTER_BASEURL + headerImagePath;
    }
}

package com.idealorb.indimovies.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.idealorb.indimovies.BuildConfig;
import com.idealorb.indimovies.R;
import com.idealorb.indimovies.adapter.ReviewAdapter;
import com.idealorb.indimovies.model.Genre;
import com.idealorb.indimovies.model.Movie;
import com.idealorb.indimovies.model.MovieDetailJsonUtil;
import com.idealorb.indimovies.model.ReleaseDate;
import com.idealorb.indimovies.model.ReleaseDatesJsonUtil;
import com.idealorb.indimovies.model.Review;
import com.idealorb.indimovies.model.Trailer;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesDetailActivity extends AppCompatActivity {

    private static final String TAG = MoviesDetailActivity.class.getSimpleName();
    private Movie movie;

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
    @BindView(R.id.movie_runtime_iv)
    ImageView runtimeIV;
    @BindView(R.id.release_date_iv)
    ImageView releaseDateIV;
    @BindView(R.id.child_toolbar)
    Toolbar toolbar;
    @BindView(R.id.review_recycler_view)
    RecyclerView reviewRecyclerView;
    @BindView(R.id.no_reviews_card)
    CardView noreviewsCard;
    ReviewAdapter reviewAdapter;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    List<Trailer> trailerList;
    private String youtubeApi;
    private YouTubePlayerSupportFragment playerFragment;
    private YouTubePlayer youTubePlayer;
    private DetailViewModel detailViewModel;


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
        trailerList = new ArrayList<>();
        List<Review> reviewList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(reviewList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        reviewRecyclerView.setLayoutManager(linearLayout);
        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.setAdapter(reviewAdapter);
        reviewRecyclerView.setNestedScrollingEnabled(false);
        Intent fromMainActivityIntent = getIntent();
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);

        if (fromMainActivityIntent != null) {
            Bundle movieBundle = fromMainActivityIntent.getBundleExtra(Intent.EXTRA_TEXT);

            movie = Parcels.unwrap(movieBundle.getParcelable("Movie"));
            loadMovieDetail(Objects.requireNonNull(movie));
        }
        movieTitleTV.setText(movie.getTitle());
        movieRatingTV.setText(String.format(Locale.getDefault(),
                "%.1f", movie.getVoteAverage()));

        movieReleaseDateTV.setText(extractYear(movie.getReleaseDate()));
        movieOverviewTV.setText(movie.getOverview());
        overviewHeaderTV.setText(R.string.movie_overview);
        runtimeIV.setImageResource(R.drawable.ic_timer);
        releaseDateIV.setImageResource(R.drawable.ic_date);
        Picasso.get()
                .load(getMovieThumbnailUrl(movie.getBackdropPath()))
                .placeholder(R.color.placeHolder)
                .into(movieHeaderIV);

        youtubeApi = BuildConfig.YouTubeApiKey;
        playerFragment = (YouTubePlayerSupportFragment) getSupportFragmentManager()
                .findFragmentById(R.id.youtube_view);

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,

                                                boolean wasRestored) {

                List<String> trailerTitleList = new ArrayList<>();
                for (int i = 0; i<trailerList.size(); i++){
                    trailerTitleList.add(trailerList.get(i).getKey());
                }

                if (!wasRestored) {
                    youTubePlayer = player;
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    youTubePlayer.loadVideos(trailerTitleList);
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playerFragment.initialize(youtubeApi, onInitializedListener);
    }

    private void loadMovieDetail(Movie movie) {
        int movieId = movie.getId();
        detailViewModel.getMovieDetails(movieId).observe(this, this::bindRemoteDataToViews);
        detailViewModel.getMovieReviews(movieId).observe(this, reviews -> {
            if (reviews != null && reviews.size() != 0) {
                showReviews();
                reviewAdapter.setReviews(reviews);
                reviewAdapter.notifyDataSetChanged();
            }else{
                showNoReviewsMessage();
            }

        });

        detailViewModel.getMovieTrailers(movieId).observe(this, trailers -> trailerList.addAll(trailers));

    }

    private void showNoReviewsMessage(){
        reviewRecyclerView.setVisibility(View.INVISIBLE);
        noreviewsCard.setVisibility(View.VISIBLE);
    }

    private void showReviews(){
        noreviewsCard.setVisibility(View.INVISIBLE);
        reviewRecyclerView.setVisibility(View.VISIBLE);
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

        List<ReleaseDatesJsonUtil> countryCerts = movieDetailJsonUtil.getReleaseDates()
                .getReleaseDatesJsonUtils();
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

package com.idealorb.indimovies.UI;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.idealorb.indimovies.BuildConfig;
import com.idealorb.indimovies.R;
import com.idealorb.indimovies.adapter.MoviesAdapter;
import com.idealorb.indimovies.adapter.MoviesAdapter.OnClickMovieListener;
import com.idealorb.indimovies.model.MoviesJsonUtil;
import com.idealorb.indimovies.model.MoviesModel;
import com.idealorb.indimovies.network.IMoviesdbApi;
import com.idealorb.indimovies.network.MoviesRemoteDataSource;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements OnClickMovieListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.tv_error_message)
    TextView errorMessageTv;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    private MoviesAdapter moviesAdapter;
    private int moviesSort = 0; //Most Popular movie (0) | Top Rated (1)
    private IMoviesdbApi moviesdbApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mainToolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById(R.id.gradientShadow).setVisibility(View.GONE);
        }

        moviesdbApi = MoviesRemoteDataSource.getRetrofitInstance()
                .create(IMoviesdbApi.class);
        List<MoviesModel> movieModels = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, movieModels);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(moviesAdapter.getItemViewType(position)){
                    case MoviesAdapter.TYPE_HEADER: return 2;
                    case MoviesAdapter.TYPE_ITEM: return 1;
                    default: return -1;
                }
            }
        });
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            gridLayoutManager = new GridLayoutManager(this, 4);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch (moviesAdapter.getItemViewType(position)) {
                        case MoviesAdapter.TYPE_HEADER:
                            return 4;
                        case MoviesAdapter.TYPE_ITEM:
                            return 1;
                        default:
                            return -1;
                    }
                }
            });
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadMovies(moviesSort);
            }
        });

        loadMovies(moviesSort);

    }

    private void showMovieDataView() {
        progressBar.setVisibility(View.INVISIBLE);
        errorMessageTv.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        errorMessageTv.setVisibility(View.VISIBLE);
    }

    private void loadMovies(final int sortOption) {
        Call<MoviesJsonUtil> retrofitCall;

        String apiKey = BuildConfig.ApiKey;
        if (sortOption == 0) {
            retrofitCall = moviesdbApi
                    .getPopularMovies(apiKey, "en-US", 1);
            asyncNetworkCall(retrofitCall, sortOption);
        } else {
            retrofitCall = moviesdbApi
                    .getTopRatedMovies(apiKey, "en-US", 1);
            asyncNetworkCall(retrofitCall, sortOption);
        }
    }

    private void asyncNetworkCall(Call<MoviesJsonUtil> retrofitCall, final int sortOption) {

        retrofitCall.enqueue(new Callback<MoviesJsonUtil>() {
            @Override
            public void onResponse(@NonNull Call<MoviesJsonUtil> call, @NonNull Response<MoviesJsonUtil> response) {
                if (response.body() != null) {
                    moviesAdapter.setMoviesList(response.body().getMovies());
                    moviesAdapter.setHeader(sortOption);
                    recyclerView.smoothScrollToPosition(0);
                    swipeRefreshLayout.setRefreshing(false);
                }
                showMovieDataView();
            }

            @Override
            public void onFailure(@NonNull Call<MoviesJsonUtil> call, @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
                showErrorMessage();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.action_most_popular){
            progressBar.setVisibility(View.VISIBLE);
            moviesSort = 0;
            loadMovies(moviesSort);
        }else if(itemId == R.id.action_highest_rated){
            progressBar.setVisibility(View.VISIBLE);
            moviesSort = 1;
            loadMovies(moviesSort);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickMovie(MoviesModel moviesModel) {

        Intent movieDetailIntent = new Intent(MainActivity.this, MoviesDetailActivity.class);
        Bundle movieBundle = new Bundle();
        Parcelable movieParcel = Parcels.wrap(moviesModel);
        movieBundle.putParcelable("Movie", movieParcel);
        movieDetailIntent.putExtra(Intent.EXTRA_TEXT, movieBundle);
        startActivity(movieDetailIntent);
    }
}

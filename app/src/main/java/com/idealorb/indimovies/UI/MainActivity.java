package com.idealorb.indimovies.UI;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.idealorb.indimovies.R;
import com.idealorb.indimovies.adapter.MoviesAdapter;
import com.idealorb.indimovies.adapter.MoviesAdapter.OnClickMovieListener;
import com.idealorb.indimovies.model.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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
    private int moviesSort = 0; //Most Popular movie (0) | Top Rated (1) | Favorite (2)
    private MainViewModel mainViewModel;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mainToolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            findViewById(R.id.gradientShadow).setVisibility(View.GONE);
        }


        List<Movie> movieModels = new ArrayList<>();
        moviesAdapter = new MoviesAdapter(this, movieModels);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (moviesAdapter.getItemViewType(position)) {
                    case MoviesAdapter.TYPE_HEADER:
                        return 2;
                    case MoviesAdapter.TYPE_ITEM:
                        return 1;
                    default:
                        return -1;
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
        swipeRefreshLayout.setOnRefreshListener(this::loadMovies);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        loadMovies();

    }

    private void loadMovies() {
        mainViewModel.getMovies(moviesSort).observe(this, movies -> {
            if (movies != null && movies.size() != 0) {
                showMovieDataView();
                moviesAdapter.setMoviesList(movies);
                moviesAdapter.setHeader(moviesSort);
                recyclerView.smoothScrollToPosition(0);
                swipeRefreshLayout.setRefreshing(false);
            }else{
                showErrorMessage();
            }
        });
    }

    private void showMovieDataView() {
        progressBar.setVisibility(View.INVISIBLE);
        errorMessageTv.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
        if (moviesSort == 2){
            errorMessageTv.setText(R.string.nofavmovies);
        }else{
            errorMessageTv.setText(R.string.error_message);
        }
        errorMessageTv.setVisibility(View.VISIBLE);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.action_favorite);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_most_popular) {
            progressBar.setVisibility(View.VISIBLE);
            menuItem.setIcon(R.drawable.ic_favorite_border);
            moviesSort = 0;
            loadMovies();
        } else if (itemId == R.id.action_highest_rated) {
            progressBar.setVisibility(View.VISIBLE);
            menuItem.setIcon(R.drawable.ic_favorite_border);
            moviesSort = 1;
            loadMovies();
        }else if(itemId ==  R.id.action_favorite){
            moviesSort = 2;
            item.setIcon(R.drawable.ic_favorite);
            loadMovies();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickMovie(Movie movie) {

        Intent movieDetailIntent = new Intent(MainActivity.this, MoviesDetailActivity.class);
        Bundle movieBundle = new Bundle();
        Parcelable movieParcel = Parcels.wrap(movie);
        movieBundle.putParcelable("Movie", movieParcel);
        movieDetailIntent.putExtra(Intent.EXTRA_TEXT, movieBundle);
        startActivity(movieDetailIntent);
    }
}

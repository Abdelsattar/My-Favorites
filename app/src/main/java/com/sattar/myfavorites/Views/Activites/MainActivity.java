package com.sattar.myfavorites.Views.Activites;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.sattar.myfavorites.Helpers.MyFavoritesApp;
import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.ViewModels.MainActivityViewModel;
import com.sattar.myfavorites.Views.Adapters.MoviesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Sattar on 2-1-2019
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    MoviesRecyclerViewAdapter recyclerViewAdapter;
    MainActivityViewModel viewModel;
    private MyFavoritesApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(getApplicationContext());
        initScreen();
    }

    void initScreen() {
        app = (MyFavoritesApp) getApplication();
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init(app.getResourceProvider());
        setUpMoviesRecyclerViewAdapter();
        updateRecyclerViewData(viewModel.getALlMovies());
    }


    void setUpMoviesRecyclerViewAdapter() {
        recyclerViewAdapter = new MoviesRecyclerViewAdapter(new ArrayList<>());
        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(recyclerViewAdapter);
        rvMovies.setLayoutManager(new GridLayoutManager(this, Utils.calculateNoOfColumns(this)));
    }

    void updateRecyclerViewData(List<Movie> movieList) {
        recyclerViewAdapter.updateData(movieList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_order_highest:
                //your code here
                orderMoviesByHighest();
                return true;
            case R.id.menu_order_lowest:
                //your code here
                orderMoviesByLowest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void orderMoviesByHighest() {
        updateRecyclerViewData(viewModel.getALlMovies());
    }


    private void orderMoviesByLowest() {
        updateRecyclerViewData(viewModel.getALlMovies());

    }
}

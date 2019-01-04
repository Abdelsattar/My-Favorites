package com.sattar.myfavorites.Views.Activites;

import android.os.Bundle;

import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.ViewModels.MainActivityViewModel;
import com.sattar.myfavorites.Views.Adapters.MoviesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sattar on 2-1-2019
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    MoviesRecyclerViewAdapter recyclerViewAdapter;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initScreen();
    }

    void initScreen() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        setUpMoviesRecyclerViewAdapter();
        updateRecyclerViewData(viewModel.getALlMovies());
    }


    void setUpMoviesRecyclerViewAdapter() {
        recyclerViewAdapter = new MoviesRecyclerViewAdapter(new ArrayList<>());
        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(recyclerViewAdapter);
    }

    void updateRecyclerViewData(List<Movie> movieList) {
        recyclerViewAdapter.updateData(movieList);
    }
}

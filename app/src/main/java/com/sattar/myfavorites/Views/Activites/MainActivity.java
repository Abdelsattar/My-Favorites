package com.sattar.myfavorites.Views.Activites;

import android.os.Bundle;

import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.Views.Adapters.MoviesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;

/**
 * Created by Sattar on 2-1-2019
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    MoviesRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initScreen();
    }

    void initScreen(){
        recyclerViewAdapter = new MoviesRecyclerViewAdapter();
        setUpMoviesRecyclerViewAdapter(new ArrayList<>());
    }


    void setUpMoviesRecyclerViewAdapter(List<Movie> movies) {

        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(recyclerViewAdapter);
    }
}

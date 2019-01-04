package com.sattar.myfavorites.Views.Activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.Views.Adapters.MoviesRecyclerViewAdapter;

import java.util.List;
/**
 * Created by Sattar on 2-1-2019
 */
public class MainActivity extends AppCompatActivity {

    MoviesRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    void setUpMoviesRecyclerViewAdapter(List<Movie> movies){

    }
}

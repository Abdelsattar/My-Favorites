package com.sattar.myfavorites.Views.Adapters;

import android.view.ViewGroup;

import com.sattar.myfavorites.Models.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sattar on 4-1-2019
 */
public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter {

    List<Movie> mMoviesList;

    public MoviesRecyclerViewAdapter(List<Movie> moviesList) {
        mMoviesList = moviesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mMoviesList != null ? mMoviesList.size() : 0;
    }

    public void updateData(List<Movie> movieList) {
        mMoviesList = movieList;
        notifyDataSetChanged();
    }
}

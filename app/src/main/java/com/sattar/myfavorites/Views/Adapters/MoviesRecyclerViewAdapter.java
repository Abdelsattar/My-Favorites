package com.sattar.myfavorites.Views.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sattar on 4-1-2019
 */
public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieHolder> {

    List<Movie> mMoviesList;

    public MoviesRecyclerViewAdapter(List<Movie> moviesList) {
        mMoviesList = moviesList;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = mMoviesList.get(position);
        holder.txtMovieName.setText(movie.getName());
        holder.txtMovieYear.setText(movie.getYear());
        try {
            holder.thumbnail.setImageResource(movie.getImageId());

        } catch (Exception e) {
            e.printStackTrace();
            holder.thumbnail.setImageResource(R.drawable.img_no_image);

        }
    }

    @Override
    public int getItemCount() {
        return mMoviesList != null ? mMoviesList.size() : 0;
    }

    public void updateData(List<Movie> movieList) {
        mMoviesList = movieList;
        notifyDataSetChanged();
    }

    static class MovieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.thumbnail)
        ImageView thumbnail;
        @BindView(R.id.txtMovieName)
        TextView txtMovieName;
        @BindView(R.id.txtMovieYear)
        TextView txtMovieYear;

        MovieHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}

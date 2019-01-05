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

    private List<Movie> mMoviesList;

    public interface ClickListener {
        void onRateClicked(View view, int pos);
    }

    ClickListener mClickListener;

    public MoviesRecyclerViewAdapter(List<Movie> moviesList, ClickListener clickListener) {
        mMoviesList = moviesList;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movie, parent, false);

        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = mMoviesList.get(position);
        holder.txtMovieName.setText(String.format("%s (%s)", movie.getName(), movie.getYear()));
        holder.txtRate.setText(String.format("%s/10", String.valueOf(movie.getRate())));
        holder.imgMore.setOnClickListener(view -> mClickListener.onRateClicked(view, position));
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
        @BindView(R.id.txtRate)
        TextView txtRate;
        @BindView(R.id.imgMore)
        ImageView imgMore;

        MovieHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }
    }
}

package com.sattar.myfavorites.Views.Activites;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.sattar.myfavorites.Helpers.MyFavoritesApp;
import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.ViewModels.MainActivityViewModel;
import com.sattar.myfavorites.Views.Adapters.MoviesRecyclerViewAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.sattar.myfavorites.Helpers.Constants.KEY_RANDOM_RATING;

/**
 * Created by Sattar on 2-1-2019
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;

    MoviesRecyclerViewAdapter recyclerViewAdapter;
    MainActivityViewModel viewModel;
    private MyFavoritesApp app;
    Float userRating;

    MoviesRecyclerViewAdapter.ClickListener cLickListener;
    private SharedPreferences preferences;
    private MenuItem randomMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(getApplicationContext());
        initScreen();
    }

    RealmResults<Movie> currentShownMovies;

    void initScreen() {
        preferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        app = (MyFavoritesApp) getApplication();
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init(app.getResourceProvider());
        currentShownMovies = viewModel.getALlMovies();
        cLickListener = this::showPopUpMenu;

        setUpMoviesRecyclerViewAdapter();
        updateRecyclerViewData();
    }

    void setUpMoviesRecyclerViewAdapter() {
        recyclerViewAdapter = new MoviesRecyclerViewAdapter(new ArrayList<>(), cLickListener);
        rvMovies.setHasFixedSize(true);
        rvMovies.setAdapter(recyclerViewAdapter);
        rvMovies.setLayoutManager(new GridLayoutManager(this, Utils.calculateNoOfColumns(this)));
    }

    void updateRecyclerViewData() {
        if (currentShownMovies != null) {
            recyclerViewAdapter.updateData(currentShownMovies);
            currentShownMovies.addChangeListener((movies, changeSet) -> updateRecyclerViewData());
        } else
            recyclerViewAdapter.updateData(new ArrayList<>());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        randomMenuItem = menu.findItem(R.id.menu_random_rating);

        if (isRandomRatingWorking()) {
            randomMenuItem.setTitle(getString(R.string.txt_stop_random_rating));
        }

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
            case R.id.menu_random_rating:
                //your code here

                if (isRandomRatingWorking()) {
                    viewModel.stopRandomRating();
                    randomMenuItem.setTitle(getString(R.string.txt_start_random_rating));
                } else {
                    viewModel.startRandomRating();
                    randomMenuItem.setTitle(getString(R.string.txt_stop_random_rating));
                }
                changeRandomRating();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void orderMoviesByHighest() {
        currentShownMovies = viewModel.getALlMoviesSortedByHighest();
        updateRecyclerViewData();
    }

    private void orderMoviesByLowest() {
        currentShownMovies = viewModel.getALlMoviesSortedByLowest();
        updateRecyclerViewData();
    }

    void showPopUpMenu(View view, int pos) {
        PopupMenu popup = new PopupMenu(MainActivity.this, view);
        popup.getMenuInflater().inflate(R.menu.rate_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(item -> {
            showRatingDialog(pos);
            return true;
        });

        popup.show();
    }

    public void showRatingDialog(int pos) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_rate);
        dialog.setTitle(R.string.txt_rate);

        TextView txtRate = dialog.findViewById(R.id.txtRate);
        TextView btnRate = dialog.findViewById(R.id.btnRate);
        TextView txtTitle = dialog.findViewById(R.id.txtTitle);

        userRating = null;
        Movie rateMovie = currentShownMovies.get(pos);

        txtTitle.append(" " + rateMovie.getName());
        MaterialRatingBar rateBar = dialog.findViewById(R.id.rateBar);
        if (rateMovie.getCurrentUserRate() != null) {
            rateBar.setRating(rateMovie.getCurrentUserRate());
            txtRate.setText(String.valueOf(rateMovie.getCurrentUserRate()));
        }
        rateBar.setOnRatingChangeListener((ratingBar, rating) -> {
            userRating = rating;
            txtRate.setText(String.format
                    ("%s/10", rating));

        });

        btnRate.setOnClickListener(view -> {
            if (userRating != null) {
                viewModel.updateUserRate(rateMovie.getId(), userRating);
                dialog.cancel();
                Toast.makeText(
                        MainActivity.this,
                        String.format(getString(R.string.txt_message_rating), rateMovie.getName(),
                                String.valueOf(userRating)),
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });
        dialog.show();
    }

    void changeRandomRating() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_RANDOM_RATING, !isRandomRatingWorking());
        editor.apply();
    }

    boolean isRandomRatingWorking() {
        return preferences.getBoolean(KEY_RANDOM_RATING, false);
    }


}


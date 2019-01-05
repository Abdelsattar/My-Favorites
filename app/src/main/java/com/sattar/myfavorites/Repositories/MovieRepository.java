package com.sattar.myfavorites.Repositories;

import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.sattar.myfavorites.Models.Movie.Fields.KEY_RATE;

/**
 * Created by Sattar on 4-1-2019
 */
public class MovieRepository {

    public MovieRepository() {
    }

    public void insertMovies(Realm mRealm, String name, String description, String imagePath, double rate) {
        mRealm.executeTransaction(realm -> {
            Movie movie = mRealm.createObject(Movie.class, Utils.generateUID());
            movie.setName(name);
            movie.setDescription(description);
            movie.setImagePath(imagePath);
            movie.setRate(rate);
        });

    }

    public List<Movie> getAllMovies(Realm realm) {
        List<Movie> moviesList = realm.where(Movie.class).findAll();
        return moviesList != null ? moviesList : new ArrayList<>();
    }

    public boolean isThereMovies(Realm realm) {
        return !realm.isEmpty();
    }

    public List<Movie> getMoviesSortedByHighest(Realm realm) {
        RealmResults<Movie> moviesList = realm.where(Movie.class).findAll();

        moviesList = moviesList.sort(KEY_RATE, Sort.DESCENDING);
        return moviesList;
    }

    public List<Movie> getMoviesSortedByLowest(Realm realm) {
        RealmResults<Movie> moviesList = realm.where(Movie.class).findAll();

        moviesList = moviesList.sort(KEY_RATE, Sort.ASCENDING);
        return moviesList;
    }
}

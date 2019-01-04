package com.sattar.myfavorites.Repositories;

import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Sattar on 4-1-2019
 */
public class MovieRepository {

    MovieRepository() {
    }

    void insertMovies(Realm mRealm, String name, String description, String imagePath, double rate) {
        mRealm.executeTransaction(realm -> {
            Movie movie = mRealm.createObject(Movie.class, Utils.generateUID());
            movie.setName(name);
            movie.setDescription(description);
            movie.setImagePath(imagePath);
            movie.setRate(rate);
        });

    }

    List<Movie> getAllMovies(Realm realm) {
        List<Movie> moviesList = realm.where(Movie.class).findAll();
        return moviesList != null ? moviesList : new ArrayList<>();
    }

    boolean isThereMovies(Realm realm) {
        return realm.isEmpty();
    }
}

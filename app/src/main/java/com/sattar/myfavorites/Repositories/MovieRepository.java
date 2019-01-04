package com.sattar.myfavorites.Repositories;

import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;

import io.realm.Realm;

/**
 * Created by Sattar on 4-1-2019
 */
public class MovieRepository {

    MovieRepository() {
    }

    void insertMovies(Realm mRealm,String name, String description, String imagePath, double rate) {
        mRealm.executeTransaction(realm -> {
            Movie movie = mRealm.createObject(Movie.class, Utils.generateUID());
            movie.setName(name);
            movie.setDescription(description);
            movie.setImagePath(imagePath);
            movie.setRate(rate);
        });

    }
}

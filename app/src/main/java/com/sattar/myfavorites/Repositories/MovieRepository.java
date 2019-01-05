package com.sattar.myfavorites.Repositories;

import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.sattar.myfavorites.Models.Movie.Fields.KEY_ID;
import static com.sattar.myfavorites.Models.Movie.Fields.KEY_RATE;

/**
 * Created by Sattar on 4-1-2019
 */
public class MovieRepository {

    public MovieRepository() {
    }

    public void insertMovies(Realm mRealm, String name, String description,
                             String year, int imageId, double rate) {
        mRealm.executeTransaction(realm -> {
            Movie movie = mRealm.createObject(Movie.class, Utils.generateUID());
            movie.setName(name);
            movie.setDescription(description);
            movie.setImageId(imageId);
            movie.setYear(year);
            movie.setRate(rate);
        });

    }

    public RealmResults<Movie> getAllMovies(Realm realm) {
        return realm.where(Movie.class).findAll();
    }

    public boolean isThereMovies(Realm realm) {
        return !realm.isEmpty();
    }

    public RealmResults<Movie> getMoviesSortedByHighest(Realm realm) {
        RealmResults<Movie> moviesList = realm.where(Movie.class).findAll();
        moviesList = moviesList.sort(KEY_RATE, Sort.DESCENDING);
        return moviesList;
    }

    public RealmResults<Movie> getMoviesSortedByLowest(Realm realm) {
        RealmResults<Movie> moviesList = realm.where(Movie.class).findAll();
        moviesList = moviesList.sort(KEY_RATE, Sort.ASCENDING);
        return moviesList;
    }

    public void updateUserRate(Realm realm, String id, float rating) {
        Movie movie = realm.where(Movie.class).equalTo(KEY_ID, id).findFirst();
        if (movie != null)
            realm.executeTransaction(realm1 -> {
                movie.setCurrentUserRate(rating);
                movie.setRate(Utils.calculateNewRating(rating, movie.getRate()));
            });
    }


}

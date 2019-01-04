package com.sattar.myfavorites.ViewModels;


//import android.arch.lifecycle.ViewModel;

import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.Repositories.MovieRepository;

import androidx.lifecycle.ViewModel;
import io.realm.Realm;

/**
 * Created by Sattar on 4-1-2019
 */
public class MainActivityViewModel extends ViewModel {

    private MovieRepository repository;
    private Realm mRealm;

    MainActivityViewModel() {
        if (mRealm == null)
            return;
        mRealm = Realm.getDefaultInstance();
        repository = new MovieRepository();

        if (!repository.isThereMovies(mRealm)) {
            initDBWithData();
        }
    }

    private void initDBWithData() {
        mRealm.executeTransaction(realm -> {
            Movie movie = mRealm.createObject(Movie.class, Utils.generateUID());
            movie.setName("X-Men");
            movie.setDescription("a science fiction movie");
            movie.setImagePath("url");
            movie.setRate(8.7);

            Movie movie2 = mRealm.createObject(Movie.class, Utils.generateUID());
            movie2.setName("X-Men");
            movie2.setDescription("a science fiction movie");
            movie2.setImagePath("url");
            movie2.setRate(8.7);

            Movie movie3 = mRealm.createObject(Movie.class, Utils.generateUID());
            movie3.setName("X-Men");
            movie3.setDescription("a science fiction movie");
            movie3.setImagePath("url");
            movie3.setRate(8.7);

            Movie movie4 = mRealm.createObject(Movie.class, Utils.generateUID());
            movie4.setName("X-Men");
            movie4.setDescription("a science fiction movie");
            movie4.setImagePath("url");
            movie4.setRate(8.7);

        });
    }

}

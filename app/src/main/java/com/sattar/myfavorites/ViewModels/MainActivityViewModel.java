package com.sattar.myfavorites.ViewModels;


//import android.arch.lifecycle.ViewModel;

import com.sattar.myfavorites.Helpers.ResourceProvider;
import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.Repositories.MovieRepository;

import androidx.lifecycle.ViewModel;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sattar on 4-1-2019
 */
public class MainActivityViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private Realm mRealm;
    private ResourceProvider mResourceProvider;

    public MainActivityViewModel() {
        if (mRealm != null)
            return;
        mRealm = Realm.getDefaultInstance();
        movieRepository = new MovieRepository();


    }

    public void init(ResourceProvider resourceProvider) {
        mResourceProvider = resourceProvider;

        if (!movieRepository.isThereMovies(mRealm)) {
            initDBWithData();
        }
    }

    private void initDBWithData() {

        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_deadpool),
                mResourceProvider.getString(R.string.txt_dead_pool_description),
                mResourceProvider.getString(R.string.txt_2016),
                R.drawable.img_dead_bool,
                8.0);

        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_home_alone),
                mResourceProvider.getString(R.string.txt_home_alone_description),
                mResourceProvider.getString(R.string.txt_1990),
                R.drawable.img_home_alone,
                7.5);
        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_incredible),
                mResourceProvider.getString(R.string.txt_incredible_description),
                mResourceProvider.getString(R.string.txt_2018),
                R.drawable.img_incredible,
                7.8);


        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_coco),
                mResourceProvider.getString(R.string.txt_coco_description),
                mResourceProvider.getString(R.string.txt_2017),
                R.drawable.img_coco,
                8.4);

        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_the_greatest),
                mResourceProvider.getString(R.string.txt_the_greatest_description),
                mResourceProvider.getString(R.string.txt_2017),
                R.drawable.img_the_greates,
                7.7);

        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_the_mountin),
                mResourceProvider.getString(R.string.txt_the_mountin_description),
                mResourceProvider.getString(R.string.txt_2017),
                R.drawable.img_the_mountain,
                6.4);

        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_harry_potter),
                mResourceProvider.getString(R.string.txt_harry_potter_description),
                mResourceProvider.getString(R.string.txt_2014),
                R.drawable.img_harry_poter,
                7.9);

        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_xen),
                mResourceProvider.getString(R.string.txt_xmen_description),
                mResourceProvider.getString(R.string.txt_2014),
                R.drawable.img_xmen,
                8.0);

        movieRepository.insertMovies(mRealm,
                mResourceProvider.getString(R.string.txt_john_wick),
                mResourceProvider.getString(R.string.txt_john_wick_description),
                mResourceProvider.getString(R.string.txt_2014),
                R.drawable.img_john_wick,
                7.3);
        
    }

    public RealmResults<Movie> getALlMovies() {
        return movieRepository.getAllMovies(mRealm);
    }

    public RealmResults<Movie> getALlMoviesSortedByHighest() {
        return movieRepository.getMoviesSortedByHighest(mRealm);
    }

    public RealmResults<Movie> getALlMoviesSortedByLowest() {
        return movieRepository.getMoviesSortedByLowest(mRealm);
    }

    public void updateUserRate(String id, float rating) {
        movieRepository.updateUserRate(mRealm, id, rating);
    }
}

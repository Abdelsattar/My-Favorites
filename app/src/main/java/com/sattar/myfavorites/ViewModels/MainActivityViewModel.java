package com.sattar.myfavorites.ViewModels;


//import android.arch.lifecycle.ViewModel;

import com.sattar.myfavorites.Helpers.ResourceProvider;
import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.Repositories.MovieRepository;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.realm.Realm;

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

        mRealm.executeTransaction(realm -> {

            Movie deadPoolMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            deadPoolMovie.setName(mResourceProvider.getString(R.string.txt_deadpool));
            deadPoolMovie.setDescription(mResourceProvider.getString(R.string.txt_dead_pool_description));
            deadPoolMovie.setImageId(R.drawable.img_dead_bool);
            deadPoolMovie.setYear(mResourceProvider.getString(R.string.txt_2016));
            deadPoolMovie.setRate(8.0);


            Movie homeAloneMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            homeAloneMovie.setName(mResourceProvider.getString(R.string.txt_home_alone));
            homeAloneMovie.setDescription(mResourceProvider.getString(R.string.txt_home_alone_description));
            homeAloneMovie.setYear(mResourceProvider.getString(R.string.txt_1990));
            homeAloneMovie.setImageId(R.drawable.img_home_alone);
            homeAloneMovie.setRate(7.5);

            Movie incredibleMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            incredibleMovie.setName(mResourceProvider.getString(R.string.txt_incredible));
            incredibleMovie.setDescription(mResourceProvider.getString(R.string.txt_incredible_description));
            incredibleMovie.setYear(mResourceProvider.getString(R.string.txt_2018));
            incredibleMovie.setImageId(R.drawable.img_incredible);
            incredibleMovie.setRate(7.8);
            Movie cocoMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            cocoMovie.setName(mResourceProvider.getString(R.string.txt_coco));
            cocoMovie.setDescription(mResourceProvider.getString(R.string.txt_coco_description));
            cocoMovie.setImageId(R.drawable.img_coco);
            cocoMovie.setYear(mResourceProvider.getString(R.string.txt_2017));
            cocoMovie.setRate(8.4);

            Movie theGratestMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            theGratestMovie.setName(mResourceProvider.getString(R.string.txt_the_greatest));
            theGratestMovie.setDescription(mResourceProvider.getString(R.string.txt_the_greatest_description));
            theGratestMovie.setYear(mResourceProvider.getString(R.string.txt_2017));
            theGratestMovie.setImageId(R.drawable.img_the_greates);
            theGratestMovie.setRate(7.7);

            Movie theMountainMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            theMountainMovie.setName(mResourceProvider.getString(R.string.txt_the_mountin));
            theMountainMovie.setDescription(mResourceProvider.getString(R.string.txt_the_mountin_description));
            theMountainMovie.setYear(mResourceProvider.getString(R.string.txt_2017));
            theMountainMovie.setImageId(R.drawable.img_the_mountain);
            theMountainMovie.setRate(6.4);

            Movie harrayPotterMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            harrayPotterMovie.setName(mResourceProvider.getString(R.string.txt_harry_potter));
            harrayPotterMovie.setDescription(mResourceProvider.getString(R.string.txt_harry_potter_description));
            harrayPotterMovie.setYear(mResourceProvider.getString(R.string.txt_2014));
            harrayPotterMovie.setRate(7.9);
            harrayPotterMovie.setImageId(R.drawable.img_harry_poter);

            Movie xmenMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            xmenMovie.setName(mResourceProvider.getString(R.string.txt_xen));
            xmenMovie.setDescription(mResourceProvider.getString(R.string.txt_xmen_description));
            xmenMovie.setYear(mResourceProvider.getString(R.string.txt_2014));
            xmenMovie.setImageId(R.drawable.img_xmen);
            xmenMovie.setRate(8.0);

            Movie johnWickMovie = mRealm.createObject(Movie.class, Utils.generateUID());
            johnWickMovie.setName(mResourceProvider.getString(R.string.txt_john_wick));
            johnWickMovie.setDescription(mResourceProvider.getString(R.string.txt_john_wick_description));
            johnWickMovie.setYear(mResourceProvider.getString(R.string.txt_2014));
            johnWickMovie.setImageId(R.drawable.img_john_wick);
            johnWickMovie.setRate(7.3);
        });
    }

    public List<Movie> getALlMovies() {
        return movieRepository.getAllMovies(mRealm);
    }

    public List<Movie> getALlMoviesSortedByHighest() {
        return movieRepository.getMoviesSortedByHighest(mRealm);
    }

    public List<Movie> getALlMoviesSortedByLowest() {
        return movieRepository.getMoviesSortedByLowest(mRealm);
    }


}

package com.sattar.myfavorites.ViewModels;

import android.util.Log;

import com.sattar.myfavorites.Helpers.ResourceProvider;
import com.sattar.myfavorites.Helpers.Utils;
import com.sattar.myfavorites.Models.Movie;
import com.sattar.myfavorites.R;
import com.sattar.myfavorites.Repositories.MovieRepository;
import com.sattar.myfavorites.Views.Activites.MainActivity;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Sattar on 4-1-2019
 */
public class MainActivityViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private Realm mRealm;
    private ResourceProvider mResourceProvider;
    private Disposable disposable;
    private int timeDelay;
    MainActivity.ShowToastListener mShowToastListener;

    public MainActivityViewModel() {
        if (mRealm != null)
            return;
        mRealm = Realm.getDefaultInstance();
        movieRepository = new MovieRepository();
    }

    public void init(ResourceProvider resourceProvider, MainActivity.ShowToastListener showToastListener) {
        mResourceProvider = resourceProvider;
        if (!movieRepository.isThereMovies(mRealm)) {
            initDBWithData();
        }
        timeDelay = Utils.getRandomDelay();

        mShowToastListener = showToastListener;
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

    public void startRandomRating() {
        getRandom();
    }

    private void getRandom() {
        Observable.fromCallable(() -> {
            Realm realm = Realm.getDefaultInstance();
            return Utils.getRandomRates(movieRepository.getMoviesSize(realm));
        }).doOnNext(doubles -> {
            timeDelay = Utils.getRandomDelay();
            if (mShowToastListener != null) {
                mShowToastListener.showToast(
                        String.format(mResourceProvider.getString(R.string.txt_toast_delay),
                                Utils.getMinutesSec(timeDelay)));
                Log.e("Toast", String.format(mResourceProvider.getString(R.string.txt_toast_delay),
                        Utils.getMinutesSec(timeDelay)));
            }
        }).repeatWhen(observable -> {
            return observable.delay(timeDelay, TimeUnit.SECONDS);
        }).subscribeOn(Schedulers.io())
                .subscribe(getObserver());
    }

    Observer getObserver() {
        return new Observer<double[]>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("Radnom", "onSubscribe");
                disposable = d;
            }

            @Override
            public void onNext(double[] rates) {
                Log.e("Radnom", rates.length + "");
                Realm realm = Realm.getDefaultInstance();
                movieRepository.updateMoviesRate(realm, rates);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Radnom", "error " + e.getMessage());

            }

            @Override
            public void onComplete() {
                Log.e("Radnom", "onComplete");

            }
        };
    }

    public void stopRandomRating() {
        if (disposable != null)
            disposable.dispose();
    }
}

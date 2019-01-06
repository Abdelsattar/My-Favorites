package com.sattar.myfavorites.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.sattar.myfavorites.Repositories.MovieRepository;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    Realm providesRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    MovieRepository providesMovieRepository() {
        return new MovieRepository();
    }

    @Provides
    SharedPreferences providesSharedPreference(Application application) {
        return application.getSharedPreferences(application.getPackageName(), Context.MODE_PRIVATE);
    }

}

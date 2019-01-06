package com.sattar.myfavorites.Helpers;

import android.app.Application;

import com.sattar.myfavorites.di.AppCompetent;
import com.sattar.myfavorites.di.AppModule;
import com.sattar.myfavorites.di.DaggerAppCompetent;

import io.realm.Realm;

/**
 * Created by Sattar on 4-1-2019
 */
public class MyFavoritesApp extends Application {

    AppCompetent mAppCompetent;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        initDagger();
    }

    void initDagger() {
        mAppCompetent = DaggerAppCompetent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppCompetent getAppCompetent() {
        return mAppCompetent;
    }


    private ResourceProvider mResourceProvider;
    public ResourceProvider getResourceProvider() {
        if (mResourceProvider == null)
            mResourceProvider = new ResourceProvider(this);

        return mResourceProvider;
    }


}

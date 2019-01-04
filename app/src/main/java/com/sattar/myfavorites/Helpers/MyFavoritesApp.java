package com.sattar.myfavorites.Helpers;

import android.app.Application;

public class MyFavoritesApp extends Application {

    private ResourceProvider mResourceProvider;
    public ResourceProvider getResourceProvider() {
        if (mResourceProvider == null)
            mResourceProvider = new ResourceProvider(this);

        return mResourceProvider;
    }
}

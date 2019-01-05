package com.sattar.myfavorites.Helpers;

import android.content.Context;

/**
 * Created by Sattar on 4-1-2019
 */
public class ResourceProvider {


    private Context mContext;

    public ResourceProvider(Context mContext) {
        this.mContext = mContext;
    }

    public String getString(int resId) {
        return mContext.getString(resId);
    }

    public String getString(int resId, Object... values) {
        return    mContext.getString(resId, values);
//        return String.format(mContext.getString(resId),values);
    }

}

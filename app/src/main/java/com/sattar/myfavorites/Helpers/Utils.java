package com.sattar.myfavorites.Helpers;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.UUID;

public class Utils {

    public static String generateUID(){
        return  UUID.randomUUID().toString();
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }
}

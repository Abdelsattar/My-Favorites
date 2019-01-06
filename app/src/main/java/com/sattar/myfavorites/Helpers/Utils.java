package com.sattar.myfavorites.Helpers;

import android.content.Context;
import android.util.DisplayMetrics;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.UUID;

public class Utils {

    public static String generateUID() {
        return UUID.randomUUID().toString();
    }

    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        return (int) (dpWidth / 180);
    }

    public static double calculateNewRating(float rating, double userRating) {
        return ((rating + userRating) / 2);
    }

    public static int getRandomDelay() {
        return (int) (Math.random() * 180) + 20;
    }

    public static double[] getRandomRates(int size) {
        double[] rates = new double[size];
        for (int i = 0; i < size; i++) {
            rates[i] = Utils.getRandomNumber(5, 10);
        }
        return rates;
    }

    static double getRandomNumber(double rangeMin, double rangeMax) {
        Random r = new Random();
        double num = rangeMin + (rangeMax - rangeMin) * r.nextDouble();

        NumberFormat formatter = new DecimalFormat("#0.0");
        return Double.valueOf(formatter.format(num));
    }

    public static String getMinutesSec(int secs) {
        return ((secs % 3600) / 60) + " minutes and " + (secs % 60) + "seconds";

    }
}

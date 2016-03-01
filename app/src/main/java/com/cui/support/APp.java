package com.cui.support;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by cuiyang on 16/3/1.
 */
public class APp extends Application {

    //When using AppCompatDelegate.MODE_NIGHT_AUTO, the time of day and your last known location
    // (if your app has the location permissions) are used to automatically switch between day and
    // night, while MODE_NIGHT_NO and MODE_NIGHT_YES forces the theme to never or always use a dark theme, respectively.
    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }
}

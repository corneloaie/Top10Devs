package com.corneloaie.android.top10devs;

import android.app.Application;

import com.corneloaie.android.top10devs.volley.VolleyHelper;

/**
 * Created by Cornel-PC on 04/03/2018.
 */

public class DefaultApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyHelper.initInstance(getApplicationContext());
    }
}

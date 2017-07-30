package com.dexter.redis;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by shikhar on 30/07/17.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

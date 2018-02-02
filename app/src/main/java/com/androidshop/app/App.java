package com.androidshop.app;

import android.app.Application;

import com.androidshop.utils.SharedUtils;

/**
 * Created by ${Terry} on 2018/2/2.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedUtils.init(this);
    }
}
